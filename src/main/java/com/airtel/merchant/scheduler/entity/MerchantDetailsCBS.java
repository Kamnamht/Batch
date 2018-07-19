package com.airtel.merchant.scheduler.entity;

/**
 * The Class MerchantDetailsCBS contains additional merchant properties which
 * will be fetched from CBS and transformed to other form.
 */
public class MerchantDetailsCBS extends MerchantDetails {

	/** The merchant trade value 5. */
	private String merchantTradeValue5;

	/** The merchant trade value 6. */
	private String merchantTradeValue6;

	/** The add line 1. */
	private String addLine1;

	/** The add line 2. */
	private String addLine2;

	/** The add line 3. */
	private String addLine3;

	/** The city. */
	private String city;

	/** The state. */
	private String state;

	/** The country. */
	private String country;

	/** The pincode. */
	private String zip;

	/**
	 * Instantiates MerchantDetailsCBS.
	 */
	public MerchantDetailsCBS() {
		super();
	}

	/**
	 * Gets the merchant trade value 5.
	 *
	 * @return the merchant trade value 5
	 */
	public String getMerchantTradeValue5() {
		return merchantTradeValue5;
	}

	/**
	 * Sets the merchant trade value 5.
	 *
	 * @param merchantTradeValue5
	 *            the new merchant trade value 5
	 */
	public void setMerchantTradeValue5(String merchantTradeValue5) {
		this.merchantTradeValue5 = merchantTradeValue5;
	}

	/**
	 * Gets the merchant trade value 6.
	 *
	 * @return the merchant trade value 6
	 */
	public String getMerchantTradeValue6() {
		return merchantTradeValue6;
	}

	/**
	 * Sets the merchant trade value 6.
	 *
	 * @param merchantTradeValue6
	 *            the new merchant trade value 6
	 */
	public void setMerchantTradeValue6(String merchantTradeValue6) {
		this.merchantTradeValue6 = merchantTradeValue6;
	}

	/**
	 * Gets the adds the line 1.
	 *
	 * @return the adds the line 1
	 */
	public String getAddLine1() {
		return addLine1;
	}

	/**
	 * Sets the adds the line 1.
	 *
	 * @param addLine1
	 *            the new adds the line 1
	 */
	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}

	/**
	 * Gets the adds the line 2.
	 *
	 * @return the adds the line 2
	 */
	public String getAddLine2() {
		return addLine2;
	}

	/**
	 * Sets the adds the line 2.
	 *
	 * @param addLine2
	 *            the new adds the line 2
	 */
	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}

	/**
	 * Gets the adds the line 3.
	 *
	 * @return the adds the line 3
	 */
	public String getAddLine3() {
		return addLine3;
	}

	/**
	 * Sets the adds the line 3.
	 *
	 * @param addLine3
	 *            the new adds the line 3
	 */
	public void setAddLine3(String addLine3) {
		this.addLine3 = addLine3;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the zip.
	 *
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Sets the zip.
	 *
	 * @param zip
	 *            the new zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

}
