package com.airtel.merchant.scheduler.dto;

/**
 * The Class MerchantDetailDTO will contains properties that will be returned
 * from merchant Bulk register API.
 */
public class MerchantDetailDTO {

	/** The uniquemid. */
	private String uniquemid;

	/** The mcc codes id. */
	private String mccCodesId;

	/** The name. */
	private String name;

	/** The contact email. */
	private String contactEmail;

	/** The contact no. */
	private String contactNo;

	/** The merchant private key. */
	private String merchantPrivateKey;

	/** The merchant public key. */
	private String merchantPublicKey;

	/**
	 * Gets the uniquemid.
	 *
	 * @return the uniquemid
	 */
	public String getUniquemid() {
		return uniquemid;
	}

	/**
	 * Sets the uniquemid.
	 *
	 * @param uniquemid
	 *            the new uniquemid
	 */
	public void setUniquemid(String uniquemid) {
		this.uniquemid = uniquemid;
	}

	/**
	 * Gets the mcc codes id.
	 *
	 * @return the mcc codes id
	 */
	public String getMccCodesId() {
		return mccCodesId;
	}

	/**
	 * Sets the mcc codes id.
	 *
	 * @param mccCodesId
	 *            the new mcc codes id
	 */
	public void setMccCodesId(String mccCodesId) {
		this.mccCodesId = mccCodesId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the contact email.
	 *
	 * @return the contact email
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * Sets the contact email.
	 *
	 * @param contactEmail
	 *            the new contact email
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * Gets the contact no.
	 *
	 * @return the contact no
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * Sets the contact no.
	 *
	 * @param contactNo
	 *            the new contact no
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * Gets the merchant private key.
	 *
	 * @return the merchant private key
	 */
	public String getMerchantPrivateKey() {
		return merchantPrivateKey;
	}

	/**
	 * Sets the merchant private key.
	 *
	 * @param merchantPrivateKey
	 *            the new merchant private key
	 */
	public void setMerchantPrivateKey(String merchantPrivateKey) {
		this.merchantPrivateKey = merchantPrivateKey;
	}

	/**
	 * Gets the merchant public key.
	 *
	 * @return the merchant public key
	 */
	public String getMerchantPublicKey() {
		return merchantPublicKey;
	}

	/**
	 * Sets the merchant public key.
	 *
	 * @param merchantPublicKey
	 *            the new merchant public key
	 */
	public void setMerchantPublicKey(String merchantPublicKey) {
		this.merchantPublicKey = merchantPublicKey;
	}

}
