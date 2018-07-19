package com.airtel.merchant.scheduler.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.exception.UPIRepositoryException;

/**
 * The Interface VoltDataProcessorRepository will provide methods that will
 * interact with VOLT DB.
 */
public interface VoltDataProcessorRepository {

	/**
	 * VoltDataProcessorRepository will interact with Volt database and create
	 * MerchantDetails list.
	 *
	 * @param upiBatchExecutionDTO
	 *            the file generator DTO
	 * @return the list
	 * @throws UPIRepositoryException
	 *             the UPI repository exception
	 */
	List<MerchantDetailsCBS> fetchMerchantsFromVolt(UPIBatchExecutionDTO upiBatchExecutionDTO)
			throws UPIRepositoryException;

	/**
	 * This method will add data to Volt DB.
	 *
	 * @param merchantDetail
	 *            the merchantDetails data.
	 * @throws UPIRepositoryException
	 *             the UPI repository exception
	 */
	void addMerchantsToVolt(MerchantDetails merchantDetail) throws UPIRepositoryException;

	/**
	 * Gets the initiation time stamp when job will start for the first time.
	 *
	 * @return the initiation time stamp
	 * @throws UPIRepositoryException
	 *             the UPI repository exception
	 */
	LocalDateTime getInitiationTimeStamp() throws UPIRepositoryException;

}
