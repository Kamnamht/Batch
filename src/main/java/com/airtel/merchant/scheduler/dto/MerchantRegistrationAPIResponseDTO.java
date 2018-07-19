package com.airtel.merchant.scheduler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class MerchantRegistrationAPIResponseDTO will contains properties that
 * will be returned from merchant Bulk register API.
 */
public class MerchantRegistrationAPIResponseDTO {

	/** The status. */
	private String status;

	/** The error msg. */
	private String errorMsg;

	/** The merchant detail. */
	@JsonProperty("MerchantDetail")
	private MerchantDetailDTO merchantDetail;

	/** The error msg. */
	private String messageText;

	/** The code. */
	private String code;

	/** The error code. */
	private String errorCode;

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error msg.
	 *
	 * @param errorMsg
	 *            the new error msg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Gets the merchant detail.
	 *
	 * @return the merchant detail
	 */
	public MerchantDetailDTO getMerchantDetail() {
		return merchantDetail;
	}

	/**
	 * Sets the merchant detail.
	 *
	 * @param merchantDetail
	 *            the new merchant detail
	 */
	public void setMerchantDetail(MerchantDetailDTO merchantDetail) {
		this.merchantDetail = merchantDetail;
	}

	/**
	 * Gets the message text.
	 *
	 * @return the message text
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * Sets the message text.
	 *
	 * @param messageText
	 *            the new message text
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode
	 *            the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
