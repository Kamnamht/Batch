package com.airtel.merchant.scheduler.enums;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;

/**
 * This enum will be used to implement Replace condition with Strategy design
 * pattern where in it will handle multiple condition checks required to create
 * enity object from resultset.
 * 
 */
public enum Evaluater {

	/** The Customer ID. */
	CUSTOMER_ID {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setCustomerId(rsValue.toString());
		}
	},

	/** The Activation date. */
	ACTIVATION_DATE {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setActivationDate(((Timestamp) rsValue).toLocalDateTime());
		}
	},

	/** The Email. */
	EMAIL {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setEmail((String) rsValue);
		}
	},

	/** The Org name. */
	ORG_NAME {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setOrgName((String) rsValue);
		}
	},

	/** The Callback URL. */
	CALLBACK_URL {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setCallbackURL((String) rsValue);
		}
	},

	/** The Merchant trade value 5. */
	MERCHANT_TRADE_VALUE_5 {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setMerchantTradeValue5((String) rsValue);
		}
	},

	/** The Merchant trade value 6. */
	MERCHANT_TRADE_VALUE_6 {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setMerchantTradeValue6((String) rsValue);
		}
	},

	/** The MC C code. */
	MCC_CODE {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setMcccode((String) rsValue);
		}
	},

	/** The Pan number. */
	PAN_NUMBER {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setPanNumber((String) rsValue);
		}
	},

	/** The Manager name. */
	MANAGER_NAME {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setManagerName((String) rsValue);
		}
	},

	/** The Add line 1. */
	ADD_LINE_1 {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setAddLine1((String) rsValue);
		}
	},

	/** The Add line 2. */
	ADD_LINE_2 {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setAddLine2((String) rsValue);
		}
	},

	/** The Add line 3. */
	ADD_LINE_3 {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setAddLine3((String) rsValue);
		}
	},

	/** The City. */
	CITY {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setCity((String) rsValue);
		}
	},

	/** The State. */
	STATE {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setState((String) rsValue);
		}
	},

	/** The Country. */
	COUNTRY {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setCountry((String) rsValue);
		}
	},

	/** The zip. */
	ZIP {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setZip((String) rsValue);
		}
	},

	/** The Address. */
	ADDRESS {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setAddress((String) rsValue);
		}
	},

	/** The Mobile. */
	MOBILE {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setMobile((String) rsValue);
		}
	},

	/** The Bank account number. */
	BANK_ACCOUNT_NUMBER {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			String actualValue = (String) rsValue;
			if (StringUtils.isEmpty(actualValue)) {
				merchantDetailsCBS.setBankAccountNo(actualValue);
			} else {
				merchantDetailsCBS.setBankAccountNo(((String) rsValue).trim());
			}
		}
	},

	/** The qr string. */
	QR_STRING {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setQrString((String) rsValue);
		}
	},

	/** The upi string. */
	UPI_STRING {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setUpiString((String) rsValue);
		}
	},

	/** The Contact name. */
	CONTACT_NAME {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setContactName((String) rsValue);
		}
	},

	/** The Operator mobile number. */
	OPERATOR_MOBILE_NUMBER {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setOperatorMobileNumber((String) rsValue);
		}
	},

	/** The Operator email. */
	OPERATOR_EMAIL {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setOperatorEmail((String) rsValue);
		}
	},

	/** The Aadhar. */
	AADHAR {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			if (null != rsValue) {
				merchantDetailsCBS.setAadhar(rsValue.toString());
			}
		}
	},

	/** The vpa. */
	VPA {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setVpa((String) rsValue);
		}
	},

	/** The link qr status. */
	LINK_QR_STATUS {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setLinkQRStatus((String) rsValue);
		}
	},

	/** The merchant register status. */
	MERCHANT_REGISTER_STATUS {

		@Override
		public void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue) {
			merchantDetailsCBS.setMerchantRegisterStatus(((String) rsValue));
		}
	};

	/**
	 * This method will be implemnted for various setters of MerchantDetailsCBS.
	 *
	 * @param merchantDetailsCBS
	 *            the merchantDetailsCBS data
	 * @param rsValue
	 *            the rs value
	 */
	public abstract void evaluate(MerchantDetailsCBS merchantDetailsCBS, Object rsValue);

}
