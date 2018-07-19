package com.airtel.merchant.scheduler.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.airtel.merchant.scheduler.cons.UpiConstants;

/**
 * The Class LinkQRRequestDTO contains properties which will be published to
 * Link Merchant API.
 */
@XmlRootElement(name = "req")
public class LinkQRRequestDTO {

	/** The version. */
	@XmlAttribute(name = "ver")
	private String version;

	/** The merchant id. */
	private String merchantId;

	/** The fe session id. */
	private String feSessionId;

	/** The actor type. */
	private String actorType;

	/** The qr string. */
	private String qrString;

	/** The upi string. */
	private String upiString;

	/**
	 * Instantiates a new link QR request DTO.
	 */
	public LinkQRRequestDTO() {
		this.version = UpiConstants.ONE_ZERO;
		this.actorType = UpiConstants.MER;
	}

	/**
	 * Gets the merchant id.
	 *
	 * @return the merchant id
	 */
	@XmlElement(name = "merchantId")
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * Sets the merchant id.
	 *
	 * @param merchantId
	 *            the new merchant id
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * Gets the fe session id.
	 *
	 * @return the fe session id
	 */
	@XmlElement(name = "feSessionId")
	public String getFeSessionId() {
		return feSessionId;
	}

	/**
	 * Sets the fe session id.
	 *
	 * @param feSessionId
	 *            the new fe session id
	 */
	public void setFeSessionId(String feSessionId) {
		this.feSessionId = feSessionId;
	}

	/**
	 * Gets the actor type.
	 *
	 * @return the actor type
	 */
	@XmlElement(name = "actorType")
	public String getActorType() {
		return actorType;
	}

	/**
	 * Gets the qr string.
	 *
	 * @return the qr string
	 */
	@XmlElement(name = "qrString")
	public String getQrString() {
		return qrString;
	}

	/**
	 * Sets the qr string.
	 *
	 * @param qrString
	 *            the new qr string
	 */
	public void setQrString(String qrString) {
		this.qrString = qrString;
	}

	/**
	 * Gets the upi string.
	 *
	 * @return the upi string
	 */
	@XmlElement(name = "upiString")
	public String getUpiString() {
		return upiString;
	}

	/**
	 * Sets the upi string.
	 *
	 * @param upiString
	 *            the new upi string
	 */
	public void setUpiString(String upiString) {
		this.upiString = upiString;
	}

}
