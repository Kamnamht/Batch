package com.airtel.merchant.scheduler.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum RSFields defines all elements fetched from resultset.
 */
public enum RSFields {

	/** The S no. */
	S_NO("S.No."),

	/** The mccr. */
	MCC_CODE("mcccode"),

	/** The Address. */
	ADDRESS("Address"),

	/** The Org code. */
	ORG_CODE("orgCode"),

	/** The callback URL. */
	CALLBACK_URL("callbackURL"),

	/** The Settlement bank. */
	SETTLEMENT_BANK("settlementBank"),

	/** The Ifsc code. */
	IFSC_CODE("ifscCode"),

	/** The Api bank. */
	API_BANK("apiBank"),

	/** The Customer ID. */
	CUSTOMER_ID("Customer Id"),

	/** The Activation date. */
	ACTIVATION_DATE("Activation Date"),

	/** The Email. */
	EMAIL("email"),

	/** The Org name. */
	ORG_NAME("orgName"),

	/** The Merchant trade value 5. */
	MERCHANT_TRADE_VALUE_5("Merchant Trade Value 5"),

	/** The Merchant trade value 6. */
	MERCHANT_TRADE_VALUE_6("Merchant Trade Value 6"),

	/** The Pan number. */
	PAN_NUMBER("panNumber"),

	/** The Manager name. */
	MANAGER_NAME("managerName"),

	/** The Add line 1. */
	ADD_LINE_1("Add Line 1"),

	/** The Add line 2. */
	ADD_LINE_2("Add Line 2"),

	/** The Add line 3. */
	ADD_LINE_3("Add Line 3"),

	/** The City. */
	CITY("City"),

	/** The State. */
	STATE("State"),

	/** The Country. */
	COUNTRY("Country"),

	/** The Pin code. */
	ZIP("ZIP"),

	/** The Mobile. */
	MOBILE("mobile"),

	/** The Bank account number. */
	BANK_ACCOUNT_NUMBER("bankAccountNo"),

	/** The Contact name. */
	CONTACT_NAME("contactName"),

	/** The Operator mobile number. */
	OPERATOR_MOBILE_NUMBER("operatorMobileNumber"),

	/** The Operator email. */
	OPERATOR_EMAIL("operatorEmail"),

	/** The Aadhar. */
	AADHAR("aadhar"),

	/** The qr string. */
	QR_STRING("QR String"),

	/** The upi string. */
	UPI_STRING("UPI String"),

	/** The vpa. */
	VPA("VPA"),

	/** The link qr status. */
	LINK_QR_STATUS("Link QR Status"),

	/** The merchant register status. */
	MERCHANT_REGISTER_STATUS("Merchant Register Status");

	/** The name. */
	private String name;

	/**
	 * The Class Holder will hold value to RSField.toString() mapping.
	 */
	private static class Holder {

		/**
		 * Instantiates a new holder.
		 */
		private Holder() {
		}

		/** The fields. */
		static Map<String, String> fields = new HashMap<>();
	}

	/**
	 * Instantiates a new RS fields.
	 *
	 * @param name
	 *            the name
	 */
	private RSFields(String name) {
		this.name = name;
		Holder.fields.put(name, this.toString());
	}

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value() {
		return name;
	}

	/**
	 * This method will return Enum value as String corresponding to String value
	 * passed to it by fetching data from cached FIELDS map of holder class.
	 *
	 * @param stringValue
	 *            the string value
	 * @return the string
	 */
	public static String enumString(String stringValue) {
		return Holder.fields.get(stringValue);
	}

}
