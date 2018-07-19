package com.airtel.merchant.scheduler.job;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.enums.ErrorCodes;
import com.airtel.merchant.scheduler.exception.UPIProcessorException;
import com.airtel.merchant.scheduler.exception.UPISchedulerException;
import com.airtel.merchant.scheduler.model.ExceptionParams;
import com.airtel.merchant.scheduler.processor.RequestGeneratorProcessor;
import com.airtel.merchant.scheduler.processor.UPIDataProcessor;
import com.airtel.merchant.scheduler.processor.UPIJobStatsProcessor;
import com.airtel.merchant.scheduler.processor.UPIWebserviceProcessor;
import com.airtel.merchant.scheduler.stats.UPIJobStats;

/**
 * Central component that will be trigger the cron jon after configurable time.
 *
 */
@Component
public class UPIRegisterationScheduler {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIRegisterationScheduler.class);

	/** The request generator processor. */
	@Autowired
	private RequestGeneratorProcessor requestGeneratorProcessor;

	/** The upi data processor. */
	@Autowired
	private UPIDataProcessor upiDataProcessor;

	/** The upi job stats processor. */
	@Autowired
	private UPIJobStatsProcessor upiJobStatsProcessor;

	/** The upi webservice processor. */
	@Autowired
	private UPIWebserviceProcessor upiWebserviceProcessor;

	/**
	 * This task will be performed on regular intervals which is configured in
	 * properties file.
	 *
	 * @throws UPISchedulerException
	 *             the UPI scheduler exception
	 */
	@Scheduled(cron = "${job.upi.cron-time}")
	public void perform() throws UPISchedulerException {

		LocalDateTime batchJobInitiationTimeStamp = LocalDateTime.now();
		try {
			if (UPIJobStats.getInstance().isJobExecuting()) {
				String reason = String.format(
						"UPI bulk registration job started at %s isn't finished yet hence terminating current execution",
						UPIJobStats.getInstance().getLastExecutionTimeStamp());
				LOGGER.error(reason);
				throw new UPISchedulerException(new ExceptionParams(ErrorCodes.UPI_500, reason));

			} else {

				upiJobStatsProcessor.addJobStatsOnStartup(batchJobInitiationTimeStamp);

				UPIBatchExecutionDTO upiBatchExecutionDTO = requestGeneratorProcessor
						.generateUPIBatchExecutionDTO(batchJobInitiationTimeStamp);

				List<MerchantDetails> merchantDetails = upiDataProcessor.fetchMerchants(upiBatchExecutionDTO);

				upiWebserviceProcessor.invokeThirdPartyAPIs(merchantDetails);

				upiJobStatsProcessor.updateOnSuccess(merchantDetails);

			}

		} catch (UPIProcessorException e) {

			throw new UPISchedulerException(e.getParams(), e.getCause());

		} finally {
			upiJobStatsProcessor.updateJobStatsOnTermination();
		}

	}
}
