package com.airtel.merchant.scheduler.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ForkJoinPoolFactoryBean;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.enums.ErrorCodes;
import com.airtel.merchant.scheduler.exception.UPIProcessorException;
import com.airtel.merchant.scheduler.exception.UPIServiceException;
import com.airtel.merchant.scheduler.model.ExceptionParams;
import com.airtel.merchant.scheduler.service.UPIDataProcessorService;
import com.airtel.merchant.scheduler.service.UPIWebserviceProcessorService;
import com.airtel.merchant.scheduler.stats.UPIJobStats;

/**
 * This class will manage all interactions to third party from UPI batch SCS.
 * 
 */
@Component
public class UPIWebserviceProcessor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIWebserviceProcessor.class);

	/** The upi webservice processor service. */
	@Autowired
	private UPIWebserviceProcessorService upiWebserviceProcessorService;

	/** The data processor service. */
	@Autowired
	private UPIDataProcessorService dataProcessorService;

	/** The forkjoin factory. */
	@Autowired
	private ForkJoinPoolFactoryBean forkjoinFactory;

	/**
	 * Invoke third party API for MW bulk registration.
	 *
	 * @param merchantDetails
	 *            the merchant details
	 * @throws UPIProcessorException
	 *             the UPI processor exception
	 */
	public void invokeThirdPartyAPIs(List<MerchantDetails> merchantDetails) throws UPIProcessorException {
		if (merchantDetails.isEmpty()) {
			String reason = String.format(
					"No merchant available for registration in batch execution %s hence terminating this execution.",
					UPIJobStats.getInstance().getExecutionCount());
			LOGGER.info(reason);
			return;
		}
		List<Callable<Boolean>> callables = new ArrayList<>();

		// invoke MW bulk registration API to register all merchants in parallel.
		merchantDetails.forEach(merchantDetail -> {
			callables.add(() -> {
				try {
					upiWebserviceProcessorService.invokeMWBulkRegistrationAPI(merchantDetail);
					if (UpiConstants.SUCCESS.equalsIgnoreCase(merchantDetail.getMerchantRegisterStatus())) {
						upiWebserviceProcessorService.invokeLinkQRAPI(merchantDetail);
						if (UpiConstants.SUCCESS.equalsIgnoreCase(merchantDetail.getLinkQRStatus())) {
							upiWebserviceProcessorService.sendSMS(merchantDetail);
						}
					}
				} catch (UPIServiceException e) {
					LOGGER.error("Merchant registration failed for merchant id: " + merchantDetail.getCustomerId()
							+ " Reason: " + e.getCause().getMessage());
					merchantDetail.setMerchantRegisterStatus(UpiConstants.FAILURE);
				} finally {
					addDataToDB(merchantDetail);
				}
				return true;
			});
		});

		List<Future<Boolean>> futures = forkjoinFactory.getObject().invokeAll(callables);
		for (Future<Boolean> future : futures) {
			try {
				future.get();
			} catch (ExecutionException | InterruptedException e) {
				String reason = "Error occured while invoking MW bulk registration API parallely.";
				LOGGER.error(reason);
				throw new UPIProcessorException(new ExceptionParams(ErrorCodes.UPI_500, reason), e.getCause());
			}
		}
	}

	/**
	 * This method will add data to Volt DB.
	 *
	 * @param merchantDetail
	 *            the merchant detail
	 * @throws UPIProcessorException
	 *             the UPI processor exception
	 */
	public void addDataToDB(MerchantDetails merchantDetail) throws UPIProcessorException {
		try {
			dataProcessorService.addDataToDB(merchantDetail);
		} catch (UPIServiceException e) {
			throw new UPIProcessorException(e.getParams(), e.getCause());
		}
	}

}
