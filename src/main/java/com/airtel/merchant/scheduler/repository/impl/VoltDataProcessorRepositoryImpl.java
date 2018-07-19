package com.airtel.merchant.scheduler.repository.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.airtel.merchant.scheduler.config.UPIQueryLoader;
import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.enums.ErrorCodes;
import com.airtel.merchant.scheduler.exception.UPIRepositoryException;
import com.airtel.merchant.scheduler.model.ExceptionParams;
import com.airtel.merchant.scheduler.repository.VoltDataProcessorRepository;
import com.airtel.merchant.scheduler.transformer.UPIBatchResultTransformer;

/**
 * The Class VoltDataProcessorRepositoryImpl provides implementation to
 * VoltDataProcessorRepository.
 */
@Transactional(value = "voltTransactionManager")
@Component
public class VoltDataProcessorRepositoryImpl implements VoltDataProcessorRepository {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(VoltDataProcessorRepositoryImpl.class);

	/** The query loader. */
	@Autowired
	private UPIQueryLoader queryLoader;

	/** The entity manager. */
	@PersistenceContext(unitName = "voltEntityManagerFactory")
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.repository.VoltDataProcessorRepository#
	 * addDataToVolt(java.util.List)
	 */
	@Override
	@Transactional(value = "voltTransactionManager")
	public void addMerchantsToVolt(MerchantDetails merchantDetail) throws UPIRepositoryException {
		LOGGER.info("Adding details of merchant - id: {} to VOLT DB.", merchantDetail.getCustomerId());
		try {
			merchantDetail.setProcessedOn(LocalDateTime.now());
			entityManager.persist(merchantDetail);
			entityManager.flush();
			entityManager.clear();
			LOGGER.debug("Merchant details for id: {} added to VOLT DB.", merchantDetail.getCustomerId());
		} catch (Exception e) {
			String reason = "Exception occured while persisting merchants to VOLT.";
			LOGGER.error(reason);
			throw new UPIRepositoryException(new ExceptionParams(ErrorCodes.UPI_500, reason));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.repository.VoltDataProcessorRepository#
	 * fetchDataFromVolt(com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<MerchantDetailsCBS> fetchMerchantsFromVolt(UPIBatchExecutionDTO upiBatchExecutionDTO)
			throws UPIRepositoryException {
		List<MerchantDetailsCBS> merchantDetailsCBS = null;
		LOGGER.info("Fetching merchant list from VOLT DB which failed to register in last batch execution.");
		try {
			String query = queryLoader.getQuery(UpiConstants.FETCH_MERCHANT_DETAILS_VOLT_QUERY);
			merchantDetailsCBS = entityManager.unwrap(Session.class).createNativeQuery(query.trim())
					.setResultTransformer(new UPIBatchResultTransformer()).list();
			LOGGER.info("Found {} merchants from VOLT.", merchantDetailsCBS.size());
		} catch (Exception e) {
			String reason = "Exception occured fetching merchants from VOLT.";
			LOGGER.error(reason);
			throw new UPIRepositoryException(new ExceptionParams(ErrorCodes.UPI_500, reason));
		}
		return merchantDetailsCBS;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.repository.UPIInitiationTimeRepository#
	 * getInitiationTimeStamp(java.time.LocalDate)
	 */
	@Override
	public LocalDateTime getInitiationTimeStamp() throws UPIRepositoryException {
		LocalDateTime startDateTime = null;
		try {
			String query = queryLoader.getQuery(UpiConstants.FETCH_FROM_TIMESTAMP_QUERY);
			Timestamp startTime = (Timestamp) entityManager.unwrap(Session.class).createNativeQuery(query.trim())
					.uniqueResult();
			if (null != startTime) {
				startDateTime = startTime.toLocalDateTime();
			}
		} catch (Exception e) {
			String reason = "Exception occured while fetching fromtimestamp from CBS.";
			LOGGER.error(reason);
			throw new UPIRepositoryException(new ExceptionParams(ErrorCodes.UPI_500, reason));
		}

		return startDateTime;
	}

}
