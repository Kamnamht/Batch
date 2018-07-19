package com.airtel.merchant.scheduler.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.airtel.merchant.scheduler.cons.UpiConstants;

/**
 * The Class MerchantDetails contains merchant details properties.
 *
 */
@Entity
@Table(name = "MERCHANT_REGISTER_BATCH_STATS")
public class MerchantDetails {

	/** The Constant SETTLEMENT_BANK. */
	private static final String SETTLEMENT_BANK = UpiConstants.SETTLEMENT_BANK;

	/** The Constant IFSC_CODE. */
	private static final String IFSC_CODE = UpiConstants.IFSC_CODE;

	/** The Constant API_BANK. */
	private static final String API_BANK = UpiConstants.API_BANK;

	/** The Constant ORG_CODE. */
	private static final String ORG_CODE = UpiConstants.ORG_CODE;

	/** The Constant PENDING. */
	private static final String PENDING = UpiConstants.PENDING;

	/** The customer id. */
	@Id
	@Column(name = "CUSTOMER_ID")
	private String customerId;

	/** The registration date. */
	@Column(name = "PROCESSED_ON")
	private LocalDateTime processedOn;

	/** The batch id. */
	@Column(name = "BATCH_ID")
	private int batchId;

	/** The activation date. */
	@Column(name = "ACTIVATION_DATE_TIME")
	private LocalDateTime activationDate;

	/** The email. */
	@Column(name = "EMAIL")
	private String email;

	/** The org name. */
	@Column(name = "ORG_NAME")
	private String orgName;

	/** The mcccode. */
	@Column(name = "MCC_CODE")
	private String mcccode;

	/** The pan number. */
	@Column(name = "PAN_NUMBER")
	private String panNumber;

	/** The aadhar. */
	@Column(name = "AADHAR")
	private String aadhar;

	/** The manager name. */
	@Column(name = "MANAGER_NAME")
	private String managerName;

	/** The address. */
	@Column(name = "ADDRESS")
	private String address;

	/** The mobile. */
	@Column(name = "MOBILE")
	private String mobile;

	/** The org code. */
	@Column(name = "ORG_CODE")
	private String orgCode;

	/** The callback URL. */
	@Column(name = "CALLBACK_URL")
	private String callbackURL;

	/** The settlement bank. */
	@Column(name = "SETTLEMENT_BANK")
	private String settlementBank;

	/** The bank account no. */
	@Column(name = "BANK_ACCOUNT_NO")
	private String bankAccountNo;

	/** The ifsc code. */
	@Column(name = "IFSC_CODE")
	private String ifscCode;

	/** The api bank. */
	@Column(name = "API_BANK")
	private String apiBank;

	/** The contact name. */
	@Column(name = "CONTACT_NAME")
	private String contactName;

	/** The operator mobile number. */
	@Column(name = "OPERATOR_MOBILE_NO")
	private String operatorMobileNumber;

	/** The operator email. */
	@Column(name = "OPERATOR_EMAIL")
	private String operatorEmail;

	/** The status. */
	@Column(name = "MERCHANT_REGISTER_STATUS")
	private String merchantRegisterStatus;

	/** The error msg. */
	@Column(name = "MERCHANT_REGISTER_ERROR")
	private String merchantRegisterErrorMessage;

	/** The uniquemid. */
	@Column(name = "UNIQUE_MID")
	private String uniquemid;

	/** The merchant private key. */
	@Column(name = "MERCHANT_PRIVATE_KEY")
	private String merchantPrivateKey;

	/** The merchant public key. */
	@Column(name = "MERCHANT_PUBLIC_KEY")
	private String merchantPublicKey;

	/** The link QR status. */
	@Column(name = "LINK_QR_STATUS")
	private String linkQRStatus;

	/** The link QR error code. */
	@Column(name = "LINK_QR_ERROR_CODE")
	private String linkQRErrorCode;

	/** The link QR message text. */
	@Column(name = "LINK_QR_MESSAGE_TEXT")
	private String linkQRMessageText;

	/** The qr string. */
	@Column(name = "QR_STRING")
	private String qrString;

	/** The upi string. */
	@Column(name = "UPI_STRING")
	private String upiString;

	/** The vpa. */
	@Column(name = "VPA")
	private String vpa;

	/**
	 * Instantiates MerchantDetails.
	 */
	public MerchantDetails() {
		super();
		this.settlementBank = SETTLEMENT_BANK;
		this.ifscCode = IFSC_CODE;
		this.apiBank = API_BANK;
		this.orgCode = ORG_CODE;
		this.merchantRegisterStatus = PENDING;
		this.linkQRStatus = PENDING;
	}

	/**
	 * Gets the customer id.
	 *
	 * @return the customer id
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the customer id.
	 *
	 * @param customerId
	 *            the new customer id
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Gets the processed on.
	 *
	 * @return the processed on
	 */
	public LocalDateTime getProcessedOn() {
		return processedOn;
	}

	/**
	 * Sets the processed on.
	 *
	 * @param processedOn
	 *            the new processed on
	 */
	public void setProcessedOn(LocalDateTime processedOn) {
		this.processedOn = processedOn;
	}

	/**
	 * Gets the batch id.
	 *
	 * @return the batch id
	 */
	public int getBatchId() {
		return batchId;
	}

	/**
	 * Sets the batch id.
	 *
	 * @param batchId
	 *            the new batch id
	 */
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	/**
	 * Gets the activation date.
	 *
	 * @return the activation date
	 */
	public LocalDateTime getActivationDate() {
		return activationDate;
	}

	/**
	 * Sets the activation date.
	 *
	 * @param activationDate
	 *            the new activation date
	 */
	public void setActivationDate(LocalDateTime activationDate) {
		this.activationDate = activationDate;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the org name.
	 *
	 * @return the org name
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * Sets the org name.
	 *
	 * @param orgName
	 *            the new org name
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * Gets the mcccode.
	 *
	 * @return the mcccode
	 */
	public String getMcccode() {
		return mcccode;
	}

	/**
	 * Sets the mcccode.
	 *
	 * @param mcccode
	 *            the new mcccode
	 */
	public void setMcccode(String mcccode) {
		this.mcccode = mcccode;
	}

	/**
	 * Gets the pan number.
	 *
	 * @return the pan number
	 */
	public String getPanNumber() {
		return panNumber;
	}

	/**
	 * Sets the pan number.
	 *
	 * @param panNumber
	 *            the new pan number
	 */
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	/**
	 * Gets the aadhar.
	 *
	 * @return the aadhar
	 */
	public String getAadhar() {
		return aadhar;
	}

	/**
	 * Sets the aadhar.
	 *
	 * @param aadhar
	 *            the new aadhar
	 */
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	/**
	 * Gets the manager name.
	 *
	 * @return the manager name
	 */
	public String getManagerName() {
		return managerName;
	}

	/**
	 * Sets the manager name.
	 *
	 * @param managerName
	 *            the new manager name
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the mobile.
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the mobile.
	 *
	 * @param mobile
	 *            the new mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the org code.
	 *
	 * @return the org code
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * Sets the org code.
	 *
	 * @param orgCode
	 *            the new org code
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * Gets the callback URL.
	 *
	 * @return the callback URL
	 */
	public String getCallbackURL() {
		return callbackURL;
	}

	/**
	 * Sets the callback URL.
	 *
	 * @param callbackURL
	 *            the new callback URL
	 */
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}

	/**
	 * Gets the settlement bank.
	 *
	 * @return the settlement bank
	 */
	public String getSettlementBank() {
		return settlementBank;
	}

	/**
	 * Sets the settlement bank.
	 *
	 * @param settlementBank
	 *            the new settlement bank
	 */
	public void setSettlementBank(String settlementBank) {
		this.settlementBank = settlementBank;
	}

	/**
	 * Gets the bank account no.
	 *
	 * @return the bank account no
	 */
	public String getBankAccountNo() {
		return bankAccountNo;
	}

	/**
	 * Sets the bank account no.
	 *
	 * @param bankAccountNo
	 *            the new bank account no
	 */
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	/**
	 * Gets the ifsc code.
	 *
	 * @return the ifsc code
	 */
	public String getIfscCode() {
		return ifscCode;
	}

	/**
	 * Sets the ifsc code.
	 *
	 * @param ifscCode
	 *            the new ifsc code
	 */
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	/**
	 * Gets the api bank.
	 *
	 * @return the api bank
	 */
	public String getApiBank() {
		return apiBank;
	}

	/**
	 * Sets the api bank.
	 *
	 * @param apiBank
	 *            the new api bank
	 */
	public void setApiBank(String apiBank) {
		this.apiBank = apiBank;
	}

	/**
	 * Gets the contact name.
	 *
	 * @return the contact name
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * Sets the contact name.
	 *
	 * @param contactName
	 *            the new contact name
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * Gets the operator mobile number.
	 *
	 * @return the operator mobile number
	 */
	public String getOperatorMobileNumber() {
		return operatorMobileNumber;
	}

	/**
	 * Sets the operator mobile number.
	 *
	 * @param operatorMobileNumber
	 *            the new operator mobile number
	 */
	public void setOperatorMobileNumber(String operatorMobileNumber) {
		this.operatorMobileNumber = operatorMobileNumber;
	}

	/**
	 * Gets the operator email.
	 *
	 * @return the operator email
	 */
	public String getOperatorEmail() {
		return operatorEmail;
	}

	/**
	 * Sets the operator email.
	 *
	 * @param operatorEmail
	 *            the new operator email
	 */
	public void setOperatorEmail(String operatorEmail) {
		this.operatorEmail = operatorEmail;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getMerchantRegisterStatus() {
		return merchantRegisterStatus;
	}

	/**
	 * Sets the status.
	 *
	 * @param merchantRegisterStatus
	 *            the new status
	 */
	public void setMerchantRegisterStatus(String merchantRegisterStatus) {
		this.merchantRegisterStatus = merchantRegisterStatus;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public String getMerchantRegisterErrorMessage() {
		return merchantRegisterErrorMessage;
	}

	/**
	 * Sets the error msg.
	 *
	 * @param merchantRegisterErrorMessage
	 *            the new error msg
	 */
	public void setMerchantRegisterErrorMessage(String merchantRegisterErrorMessage) {
		this.merchantRegisterErrorMessage = merchantRegisterErrorMessage;
	}

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

	/**
	 * Gets the link QR status.
	 *
	 * @return the link QR status
	 */
	public String getLinkQRStatus() {
		return linkQRStatus;
	}

	/**
	 * Sets the link QR status.
	 *
	 * @param linkQRStatus
	 *            the new link QR status
	 */
	public void setLinkQRStatus(String linkQRStatus) {
		this.linkQRStatus = linkQRStatus;
	}

	/**
	 * Gets the link QR error code.
	 *
	 * @return the link QR error code
	 */
	public String getLinkQRErrorCode() {
		return linkQRErrorCode;
	}

	/**
	 * Sets the link QR error code.
	 *
	 * @param linkQRErrorCode
	 *            the new link QR error code
	 */
	public void setLinkQRErrorCode(String linkQRErrorCode) {
		this.linkQRErrorCode = linkQRErrorCode;
	}

	/**
	 * Gets the link QR message text.
	 *
	 * @return the link QR message text
	 */
	public String getLinkQRMessageText() {
		return linkQRMessageText;
	}

	/**
	 * Sets the link QR message text.
	 *
	 * @param linkQRMessageText
	 *            the new link QR message text
	 */
	public void setLinkQRMessageText(String linkQRMessageText) {
		this.linkQRMessageText = linkQRMessageText;
	}

	/**
	 * Gets the qr string.
	 *
	 * @return the qr string
	 */
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

	/**
	 * Gets the vpa.
	 *
	 * @return the vpa
	 */
	public String getVpa() {
		return vpa;
	}

	/**
	 * Sets the vpa.
	 *
	 * @param vpa
	 *            the new vpa
	 */
	public void setVpa(String vpa) {
		this.vpa = vpa;
	}

}
