package com.airtel.merchant.scheduler.dto;

/**
 * The Class MerchantRegistrationAPIRequestDTO will contain properties that will
 * be published to MW Bulk registration API.
 */
public class MerchantRegistrationAPIRequestDTO {

	/** The fe session id. */
	private String feSessionId;

	/** The data. */
	private MWAPIMerchantDetailsDTO data;

	/**
	 * Gets the fe session id.
	 *
	 * @return the fe session id
	 */
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
	 * Gets the data.
	 *
	 * @return the data
	 */
	public MWAPIMerchantDetailsDTO getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the new data
	 */
	public void setData(MWAPIMerchantDetailsDTO data) {
		this.data = data;
	}

}
