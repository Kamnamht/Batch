package com.airtel.merchant.scheduler.service;

import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.exception.UPIServiceException;

/**
 * This interface defines methods which will generate URI MW bulk registration
 * API.
 *
 */
public interface UPIWebserviceProcessorService {

	/**
	 * Invoke MW bulk registration API.
	 *
	 * @param merchantDetail
	 *            the merchantDetail data
	 * @return the response entity
	 * @throws UPIServiceException
	 *             the UPI service exception
	 */
	void invokeMWBulkRegistrationAPI(MerchantDetails merchantDetail) throws UPIServiceException;

	/**
	 * This method will help in sending SMS message to successfully registered
	 * merchant.
	 *
	 * @param merchantDetail
	 *            the merchant detail
	 * @throws UPIServiceException
	 *             the UPI service exception
	 */
	void sendSMS(MerchantDetails merchantDetail) throws UPIServiceException;

	/**
	 * This method will invoke Link QR code functionality to insert generated UPI
	 * and QR string to CBS.
	 *
	 * @param merchantDetail
	 *            the merchant detail
	 * @throws UPIServiceException
	 *             the UPI service exception
	 */
	void invokeLinkQRAPI(MerchantDetails merchantDetail) throws UPIServiceException;

}
