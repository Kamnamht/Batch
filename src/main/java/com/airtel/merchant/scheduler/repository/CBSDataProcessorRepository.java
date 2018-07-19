package com.airtel.merchant.scheduler.repository;

import java.util.List;

import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.exception.UPIRepositoryException;

/**
 * This interface baselines the methods which will help in accessing data from
 * CBS via repository layer.
 * 
 */
public interface CBSDataProcessorRepository {

	/**
	 * UPIDataProcessorRepository will interact with CBS database and create
	 * MerchantDetailsCBS.
	 *
	 * @param upiBatchExecutionDTO
	 *            the upi batch execution DTO
	 * @return the list
	 * @throws UPIRepositoryException
	 *             the UPI repository exception
	 */
	List<MerchantDetailsCBS> fetchMerchantsFromCBS(UPIBatchExecutionDTO upiBatchExecutionDTO)
			throws UPIRepositoryException;

}
