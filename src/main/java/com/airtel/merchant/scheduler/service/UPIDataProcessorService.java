package com.airtel.merchant.scheduler.service;

import java.time.LocalDateTime;
import java.util.List;

import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.exception.UPIServiceException;

/**
 * This interface baselines the methods which will help in accessing data from
 * CBS via repository layer.
 * 
 */
public interface UPIDataProcessorService {

	/**
	 * This method will interact with repository to fetch UPI related data and feed
	 * it to the processor.
	 *
	 * @param upiBatchExecutionDTO
	 *            the file generator DTO
	 * @return the list
	 * @throws UPIServiceException
	 *             the UPI service exception
	 */
	List<MerchantDetailsCBS> fetchMerchants(UPIBatchExecutionDTO upiBatchExecutionDTO) throws UPIServiceException;

	/**
	 * This method will add data to Volt DB.
	 *
	 * @param merchantDetail
	 *            the merchantDetails
	 * @throws UPIServiceException
	 *             the UPI service exception
	 */
	void addDataToDB(MerchantDetails merchantDetail) throws UPIServiceException;

	/**
	 * Gets the initiation time stamp.
	 *
	 * @return the initiation time stamp
	 * @throws UPIServiceException
	 *             the UPI service exception
	 */
	LocalDateTime getInitiationTimeStamp() throws UPIServiceException;

}
