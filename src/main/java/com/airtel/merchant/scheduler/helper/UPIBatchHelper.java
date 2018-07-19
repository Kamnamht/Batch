/*
 *
 */
package com.airtel.merchant.scheduler.helper;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.dto.LinkQRRequestDTO;
import com.airtel.merchant.scheduler.dto.MWAPIMerchantDetailsDTO;
import com.airtel.merchant.scheduler.dto.MerchantRegistrationAPIRequestDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.model.CommunicationListModel;
import com.airtel.merchant.scheduler.model.SendSMSModel;
import com.airtel.merchant.scheduler.security.TripleDESCrypt;
import com.airtel.merchant.scheduler.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class UPIBatchHelper contains helper methods to support Processors,
 * Services and Repository.
 */
@Component
public class UPIBatchHelper {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIBatchHelper.class);

	/** The mapper. */
	@Autowired
	private ObjectMapper mapper;

	/** The text message. */
	@Value("${send.sms.ws.text.message}")
	private String textMessage;

	/** The text message sender. */
	@Value("${send.sms.ws.sender.name}")
	private String textMessageSender;

	/** The qr string encryption key. */
	@Value("${qr.string.encryption.key}")
	private String qrStringEncryptionKey;

	/** The qr code URI. */
	@Value("${qr.code.uri}")
	private String qrCodeURI;

	/** The merchant upi QR uri. */
	@Value("${merchant.upi.qr.uri}")
	private String merchantUpiQRUri;

	/** The merchant upi QR handle. */
	@Value("${merchant.upi.qr.handle}")
	private String merchantUpiQRHandle;

	/** The merchant upi TN narration. */
	@Value("${merchant.upi.tn.narration}")
	private String merchantUpiTNNarration;

	/**
	 * This method will return SMS model, to be passed to Send SMS API.
	 *
	 * @param custNatlId
	 *            the cust natl id
	 * @param extRefNumber
	 *            the email
	 * @return the send SMS model
	 */
	public SendSMSModel prepareSendSMSObject(String custNatlId, String extRefNumber) {
		final CommunicationListModel communicationListModel = new CommunicationListModel();
		communicationListModel.setContent(textMessage);
		communicationListModel.setSender(textMessageSender);
		communicationListModel.setExternalRefNo(extRefNumber);

		communicationListModel.setType(UpiConstants.SMS);
		communicationListModel.setMode(UpiConstants.I);
		communicationListModel.setLangId(UpiConstants.ONE);
		communicationListModel.setSubject(UpiConstants.SMS);
		communicationListModel.setCategory(UpiConstants.TRANSACTION);

		final List<String> recipients = new ArrayList<>();
		recipients.add(custNatlId);
		communicationListModel.setRecipient(recipients);

		final List<CommunicationListModel> communicationList = new ArrayList<>();
		communicationList.add(communicationListModel);
		final SendSMSModel sendSMSModel = new SendSMSModel();
		sendSMSModel.setCommunicationList(communicationList);
		return sendSMSModel;
	}

	/**
	 * This method prepares MerchantRegistrationAPIRequestDTO object.
	 *
	 * @param merchantDetail
	 *            the merchant detail
	 * @return the merchant and til details
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public MerchantRegistrationAPIRequestDTO getMerchantRegistrationAPIRequestDTO(MerchantDetails merchantDetail)
			throws IOException {
		MerchantRegistrationAPIRequestDTO requestDTO = new MerchantRegistrationAPIRequestDTO();
		requestDTO.setFeSessionId(randomString(15));
		requestDTO.setData(getMWAPIMerchantDetailsDTO(merchantDetail));
		String requestString = JsonUtils.entityToJson(requestDTO, mapper);
		LOGGER.debug("Merchant registration API Request body: {}", requestString);
		requestDTO.getData().setAadhar(merchantDetail.getAadhar());
		requestDTO.getData().setBankAccountNo(merchantDetail.getBankAccountNo());
		requestDTO.getData().setPanNumber(merchantDetail.getPanNumber());
		return requestDTO;
	}

	/**
	 * Gets the link QR request DTO.
	 *
	 * @param merchantDetail
	 *            the merchant detail
	 * @param feSessionId
	 *            the fe session id
	 * @return the link QR request DTO
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public LinkQRRequestDTO getLinkQRRequestDTO(MerchantDetails merchantDetail, String feSessionId) throws IOException {
		LinkQRRequestDTO linkQRRequestDTO = new LinkQRRequestDTO();
		linkQRRequestDTO.setFeSessionId(feSessionId);
		linkQRRequestDTO.setMerchantId(merchantDetail.getMobile());
		linkQRRequestDTO.setQrString(merchantDetail.getQrString());
		linkQRRequestDTO.setUpiString(merchantDetail.getUpiString());
		String requestString = JsonUtils.entityToJson(linkQRRequestDTO, mapper);
		LOGGER.debug("Link QR API Request body: {}", requestString);
		return linkQRRequestDTO;
	}

	/**
	 * Gets the MWAPI merchant details DTO.
	 *
	 * @param merchantDetail
	 *            the merchant detail
	 * @return the MWAPI merchant details DTO
	 */
	private MWAPIMerchantDetailsDTO getMWAPIMerchantDetailsDTO(MerchantDetails merchantDetail) {
		MWAPIMerchantDetailsDTO mwAPIMerchantDetailsDTO = new MWAPIMerchantDetailsDTO();
		mwAPIMerchantDetailsDTO.setCustomerId(merchantDetail.getCustomerId());
		mwAPIMerchantDetailsDTO.setAadhar(UpiConstants.MASK);
		mwAPIMerchantDetailsDTO.setBankAccountNo(UpiConstants.MASK);
		mwAPIMerchantDetailsDTO.setPanNumber(UpiConstants.MASK);
		mwAPIMerchantDetailsDTO.setAddress(merchantDetail.getAddress());
		mwAPIMerchantDetailsDTO.setApiBank(merchantDetail.getApiBank());
		mwAPIMerchantDetailsDTO.setCallbackURL(merchantDetail.getCallbackURL());
		mwAPIMerchantDetailsDTO.setContactName(merchantDetail.getContactName());
		mwAPIMerchantDetailsDTO.setEmail(merchantDetail.getEmail());
		mwAPIMerchantDetailsDTO.setIfscCode(merchantDetail.getIfscCode());
		mwAPIMerchantDetailsDTO.setManagerName(merchantDetail.getManagerName());
		mwAPIMerchantDetailsDTO.setMcccode(merchantDetail.getMcccode());
		mwAPIMerchantDetailsDTO.setMobile(merchantDetail.getMobile());
		mwAPIMerchantDetailsDTO.setOperatorEmail(merchantDetail.getOperatorEmail());
		mwAPIMerchantDetailsDTO.setOperatorMobileNumber(merchantDetail.getOperatorMobileNumber());
		mwAPIMerchantDetailsDTO.setOrgCode(merchantDetail.getOrgCode());
		mwAPIMerchantDetailsDTO.setOrgName(merchantDetail.getOrgName());
		mwAPIMerchantDetailsDTO.setActivationDate(merchantDetail.getActivationDate());
		mwAPIMerchantDetailsDTO.setSettlementBank(merchantDetail.getSettlementBank());
		mwAPIMerchantDetailsDTO.setVpa(merchantDetail.getVpa());
		return mwAPIMerchantDetailsDTO;
	}

	/**
	 * This method will generate UPI QR string from QR code in DB != null ? generate
	 * UPI : generate QR and generate UPI.
	 *
	 * @return the string
	 */
	public String generateQRString() {
		LOGGER.debug("Generating QR String as its is not available.");
		StringBuilder sb = new StringBuilder("");
		sb.append(UpiConstants.UPI_STRING).append(System.currentTimeMillis()).append(UpiConstants.UNDERSCORE)
				.append(randomString(4));
		return sb.toString();

	}

	/**
	 * This method will generate random String.
	 *
	 * @param len
	 *            the len
	 * @return the string
	 */
	public String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		final String data = UpiConstants.RANDOM_STRING;
		SecureRandom rnd = new SecureRandom();
		for (int i = 0; i < len; i++)
			sb.append(data.charAt(rnd.nextInt(data.length())));
		return sb.toString();
	}

	/**
	 * This method will generate UPI string from QR String present in DB and encode
	 * it.
	 *
	 * @param merchantDetailsCBS
	 *            the merchant details CBS
	 * @param qrString
	 *            the qr string
	 * @return the string
	 */
	@SuppressWarnings("deprecation")
	public String generateUPIFromQR(MerchantDetailsCBS merchantDetailsCBS, String qrString) {
		StringBuilder sb = new StringBuilder();
		sb.append(merchantUpiQRUri).append(UpiConstants.PA).append(merchantDetailsCBS.getMobile())
				.append(merchantUpiQRHandle).append(UpiConstants.PN).append(merchantDetailsCBS.getContactName())
				.append(UpiConstants.TN).append(merchantUpiTNNarration).append(UpiConstants.MC)
				.append(merchantDetailsCBS.getMcccode());
		try {
			String encryptedQRString = TripleDESCrypt.encrypt(qrString, qrStringEncryptionKey);
			sb.append(UpiConstants.RA).append(qrCodeURI).append(UpiConstants.DATA).append(encryptedQRString);
		} catch (Exception e) {
			String reason = String.format("Error occured while generating UPI QR for merchant id: %s",
					merchantDetailsCBS.getCustomerId());
			LOGGER.error(reason);
		}
		String upiString = sb.toString();
		LOGGER.debug("Generated UPI string for merchant id: {} is: {}", merchantDetailsCBS.getCustomerId(), upiString);
		return URLEncoder.encode(upiString);
	}
}
