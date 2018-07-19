package com.airtel.merchant.scheduler.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.exception.UPIProcessorException;
import com.airtel.merchant.scheduler.exception.UPIServiceException;
import com.airtel.merchant.scheduler.service.UPIDataProcessorService;
import com.airtel.merchant.scheduler.stats.UPIJobStats;

/**
 * UPIDataProcessor will take the request DTO and filter out data from DB
 * applying the required criteria.
 * 
 */
@Component
public class UPIDataProcessor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIDataProcessor.class);

	/** The data processor service. */
	@Autowired
	private UPIDataProcessorService dataProcessorService;

	/**
	 * This method will do processing on input data and invoke service class to
	 * fetch data from DB.
	 *
	 * @param upiBatchExecutionDTO
	 *            the file generator DTO
	 * @return the list
	 * @throws UPIProcessorException
	 *             the UPI processor exception
	 */
	public List<MerchantDetails> fetchMerchants(UPIBatchExecutionDTO upiBatchExecutionDTO)
			throws UPIProcessorException {

		try {
			LOGGER.debug(
					"Invoked data processor service to fetch new merchants from CBS and registration failed merchants from VOLT");
			return getMerchantDetails(dataProcessorService.fetchMerchants(upiBatchExecutionDTO));
		} catch (UPIServiceException e) {
			throw new UPIProcessorException(e.getParams(), e.getCause());
		}

	}

	/**
	 * Gets the merchant details.
	 *
	 * @param merchantDetailsCBS
	 *            the merchant details CBS
	 * @return the merchant details
	 */
	private List<MerchantDetails> getMerchantDetails(List<MerchantDetailsCBS> merchantDetailsCBS) {
		LOGGER.debug("Transforming MerchantDetailsCBS to MerchantDetails");
		List<MerchantDetails> merchantDetails = Collections.synchronizedList(new ArrayList<>());
		merchantDetailsCBS.forEach(merchantDetailCBS -> {
			MerchantDetails merchantDetail = new MerchantDetails();
			merchantDetail.setCustomerId(merchantDetailCBS.getCustomerId());
			merchantDetail.setAadhar(merchantDetailCBS.getAadhar());
			merchantDetail.setAddress(merchantDetailCBS.getAddress());
			merchantDetail.setApiBank(merchantDetailCBS.getApiBank());
			merchantDetail.setBankAccountNo(merchantDetailCBS.getBankAccountNo());
			merchantDetail.setBatchId(UPIJobStats.getInstance().getExecutionCount());
			merchantDetail.setCallbackURL(merchantDetailCBS.getCallbackURL());
			merchantDetail.setContactName(merchantDetailCBS.getContactName());
			merchantDetail.setEmail(merchantDetailCBS.getEmail());
			merchantDetail.setIfscCode(merchantDetailCBS.getIfscCode());
			merchantDetail.setManagerName(merchantDetailCBS.getManagerName());
			merchantDetail.setMcccode(merchantDetailCBS.getMcccode());
			merchantDetail.setMobile(merchantDetailCBS.getMobile());
			merchantDetail.setOperatorEmail(merchantDetailCBS.getOperatorEmail());
			merchantDetail.setOperatorMobileNumber(merchantDetailCBS.getOperatorMobileNumber());
			merchantDetail.setOrgCode(merchantDetailCBS.getOrgCode());
			merchantDetail.setOrgName(merchantDetailCBS.getOrgName());
			merchantDetail.setPanNumber(merchantDetailCBS.getPanNumber());
			merchantDetail.setProcessedOn(merchantDetailCBS.getProcessedOn());
			merchantDetail.setActivationDate(merchantDetailCBS.getActivationDate());
			merchantDetail.setSettlementBank(merchantDetailCBS.getSettlementBank());
			merchantDetail.setMerchantRegisterStatus(merchantDetailCBS.getMerchantRegisterStatus());
			merchantDetail.setQrString(merchantDetailCBS.getQrString());
			merchantDetail.setUpiString(merchantDetailCBS.getUpiString());
			merchantDetail.setVpa(merchantDetailCBS.getVpa());
			merchantDetail.setLinkQRStatus(merchantDetailCBS.getLinkQRStatus());
			merchantDetails.add(merchantDetail);
		});
		return merchantDetails;

	}

}
