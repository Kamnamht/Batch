package com.airtel.merchant.scheduler.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class LinkQRResponseDTO, represents properties which will be received
 * from Link QR API.
 */
@XmlRootElement(name = "res")
public class LinkQRResponseDTO {

	/** The code. */
	private String code;

	/** The error code. */
	private String errorCode;

	/** The message text. */
	private String messageText;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	@XmlAttribute(name = "code")
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
	@XmlElement(name = "errorCode")
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

	/**
	 * Gets the message text.
	 *
	 * @return the message text
	 */
	@XmlElement(name = "messageText")
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Code: ").append(this.code).append(" Error code: ").append(this.errorCode).append(" Error message: ")
				.append(this.messageText);
		return sb.toString();
	}

}
