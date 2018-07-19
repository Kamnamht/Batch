package com.airtel.merchant.scheduler.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.config.Config.MerchantTradeValues.MerchantTradeValue5.MerchantTradeValue6;
import com.airtel.merchant.scheduler.config.MCCConfigLoader;
import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.exception.UPIRepositoryException;
import com.airtel.merchant.scheduler.exception.UPIServiceException;
import com.airtel.merchant.scheduler.helper.UPIBatchHelper;
import com.airtel.merchant.scheduler.repository.CBSDataProcessorRepository;
import com.airtel.merchant.scheduler.repository.VoltDataProcessorRepository;
import com.airtel.merchant.scheduler.service.UPIDataProcessorService;

/**
 * The Class UPIDataProcessorServiceImpl provides implementation to all base
 * line methods in UPIDataProcessorService.
 */
@Component
public class UPIDataProcessorServiceImpl implements UPIDataProcessorService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIDataProcessorServiceImpl.class);

	/** The upi data processor repository. */
	@Autowired
	private CBSDataProcessorRepository cbsDataProcessorRepository;

	/** The volt data processor repository. */
	@Autowired
	private VoltDataProcessorRepository voltDataProcessorRepository;

	/** The mcc config loader. */
	@Autowired
	private MCCConfigLoader mccConfigLoader;

	/** The upi batch helper. */
	@Autowired
	private UPIBatchHelper upiBatchHelper;

	/** The call back URL. */
	@Value("${mcc.config.file.callback.url}")
	private String callBackURL;

	/** The sample email. */
	@Value("${mcc.config.file.sample.email.domain}")
	private String sampleEmailDomain;

	/** The merchant upi QR handle. */
	@Value("${merchant.upi.qr.handle}")
	private String merchantUpiQRHandle;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.service.UPIDataProcessorService#
	 * fetchDataFromCBS(com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO)
	 */
	@Override
	public List<MerchantDetailsCBS> fetchMerchants(UPIBatchExecutionDTO upiBatchExecutionDTO)
			throws UPIServiceException {
		try {
			List<MerchantDetailsCBS> merchantDetailsCBS = transform(
					cbsDataProcessorRepository.fetchMerchantsFromCBS(upiBatchExecutionDTO));
			List<MerchantDetailsCBS> merchantDetails = voltDataProcessorRepository
					.fetchMerchantsFromVolt(upiBatchExecutionDTO);
			if (null != merchantDetails && !merchantDetails.isEmpty()) {
				merchantDetailsCBS.addAll(merchantDetails);
			}
			return merchantDetailsCBS;
		} catch (UPIRepositoryException e) {
			throw new UPIServiceException(e.getParams(), e.getCause());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.service.UPIInitiationTimeService#
	 * getInitiationTimeStamp(java.time.LocalDate)
	 */
	@Override
	public LocalDateTime getInitiationTimeStamp() throws UPIServiceException {
		try {
			return voltDataProcessorRepository.getInitiationTimeStamp();
		} catch (UPIRepositoryException e) {
			throw new UPIServiceException(e.getParams(), e.getCause());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.airtel.merchant.scheduler.service.UPIDataProcessorService#addDataToVolt(
	 * java.util.List)
	 */
	@Override
	public void addDataToDB(MerchantDetails merchantDetail) throws UPIServiceException {
		try {
			voltDataProcessorRepository.addMerchantsToVolt(merchantDetail);
		} catch (UPIRepositoryException e) {
			throw new UPIServiceException(e.getParams(), e.getCause());
		}
	}

	/**
	 * This methods will validate, transform and add some default values to null
	 * values.
	 *
	 * @param merchantDetailsCBS
	 *            the merchantDetailsCBS data
	 * @return the list
	 */
	private List<MerchantDetailsCBS> transform(List<MerchantDetailsCBS> merchantDetailsCBS) {
		LOGGER.debug("Transforming generated data.");
		merchantDetailsCBS.forEach(merchantDetailCBS -> {
			merchantDetailCBS.setAddress(UpiConstants.NA);
			merchantDetailCBS.setPanNumber(UpiConstants.NA);
			merchantDetailCBS.setAadhar(UpiConstants.NA);
			merchantDetailCBS.setCallbackURL(callBackURL);
			merchantDetailCBS.setMcccode(lookUpMCCCode(merchantDetailCBS));
			if (StringUtils.isEmpty(merchantDetailCBS.getEmail())) {
				StringBuilder sb = new StringBuilder(merchantDetailCBS.getMobile()).append(sampleEmailDomain);
				merchantDetailCBS.setEmail(sb.toString());
			}
			if (StringUtils.isEmpty(merchantDetailCBS.getOperatorEmail())) {
				merchantDetailCBS.setOperatorEmail(UpiConstants.NA);
			}

			if (StringUtils.isEmpty(merchantDetailCBS.getQrString())) {
				merchantDetailCBS.setQrString(upiBatchHelper.generateQRString());
			}
			if (StringUtils.isEmpty(merchantDetailCBS.getUpiString())) {
				merchantDetailCBS.setUpiString(
						upiBatchHelper.generateUPIFromQR(merchantDetailCBS, merchantDetailCBS.getQrString()));
				merchantDetailCBS.setVpa(merchantDetailCBS.getMobile() + merchantUpiQRHandle);
			} else {
				merchantDetailCBS
						.setVpa(filterPAKeyData(merchantDetailCBS.getUpiString(), merchantDetailCBS.getCustomerId()));
			}
		});
		return merchantDetailsCBS;
	}

	/**
	 * Filter PA key data will extract data associated with pa key in VPA string.
	 *
	 * @param upiString
	 *            the vpa
	 * @param merchantId
	 *            the merchant id
	 * @return the string
	 */
	private String filterPAKeyData(String upiString, String merchantId) {
		LOGGER.debug("Filtering out vpa from complete vpa string.");
		String paKeyValue = upiString;
		if (null != upiString) {
			try {
				String decodedString = URLDecoder.decode(upiString, UpiConstants.UTF_8);
				for (String str : decodedString.split(UpiConstants.AMPERSAND)) {
					if (str.contains(UpiConstants.PA)) {
						paKeyValue = str.trim().substring(str.indexOf(UpiConstants.EQUAL) + 1, str.length());
						break;
					}
				}
			} catch (UnsupportedEncodingException e) {
				String reason = String.format("Unsupported encoding of UPI String %s for merchant id %s. Reason: %s",
						upiString, merchantId, e.getMessage());
				LOGGER.error(reason);
			}
		}
		return paKeyValue;

	}

	/**
	 * This method will lookup for MCC code based on parameters present in
	 * merchantDetailsCBS.
	 *
	 * @param merchantDetailsCBS
	 *            the merchantDetailsCBS data
	 * @return the string
	 */
	private String lookUpMCCCode(MerchantDetailsCBS merchantDetailsCBS) {
		LOGGER.debug(
				"Lookup mcccode from cached mcccode map for merchant {} with Trader Value 5: {} and Trade value 6: {}",
				merchantDetailsCBS.getCustomerId(), merchantDetailsCBS.getMerchantTradeValue5(),
				merchantDetailsCBS.getMerchantTradeValue6());
		String mccCode = null;
		Map<String, List<MerchantTradeValue6>> mccConfigMap = mccConfigLoader.getMccConfigMap();
		if (null != mccConfigMap) {
			try {
				List<MerchantTradeValue6> mccTradeValue6List = mccConfigMap
						.get(merchantDetailsCBS.getMerchantTradeValue5());
				if (null != mccTradeValue6List) {
					Optional<MerchantTradeValue6> mTV6 = mccTradeValue6List.stream()
							.filter(mccTradeValue6 -> merchantDetailsCBS.getMerchantTradeValue6()
									.equalsIgnoreCase(mccTradeValue6.getId()))
							.findFirst();
					if (mTV6.isPresent()) {
						mccCode = mTV6.get().getMcc();
					}

				} else {
					LOGGER.error(
							"MCC Code not found for merchant id: {} with Merchant trade value 5: {} and Merchant trade value 6 as: {}",
							merchantDetailsCBS.getCustomerId(), merchantDetailsCBS.getMerchantTradeValue5(),
							merchantDetailsCBS.getMerchantTradeValue6());
				}
			} catch (NoSuchElementException e) {
				LOGGER.error(
						"MCC Code not found for merchant id: {} with Merchant trade value 5: {} and Merchant trade value 6 as: {}",
						merchantDetailsCBS.getCustomerId(), merchantDetailsCBS.getMerchantTradeValue5(),
						merchantDetailsCBS.getMerchantTradeValue6());
			}

		}
		return mccCode;
	}

}
