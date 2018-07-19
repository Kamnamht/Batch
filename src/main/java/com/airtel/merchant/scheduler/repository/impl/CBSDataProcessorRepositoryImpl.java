package com.airtel.merchant.scheduler.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.airtel.merchant.scheduler.config.UPIQueryLoader;
import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetailsCBS;
import com.airtel.merchant.scheduler.enums.ErrorCodes;
import com.airtel.merchant.scheduler.exception.UPIRepositoryException;
import com.airtel.merchant.scheduler.model.ExceptionParams;
import com.airtel.merchant.scheduler.repository.CBSDataProcessorRepository;
import com.airtel.merchant.scheduler.transformer.UPIBatchResultTransformer;

/**
 * The Class UPIDataProcessorRepositoryImpl provides implementation to all
 * baseline methods in UPIDataProcessorRepository.
 */
@Transactional
@Component
public class CBSDataProcessorRepositoryImpl implements CBSDataProcessorRepository {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CBSDataProcessorRepositoryImpl.class);

	/** The query loader. */
	@Autowired
	private UPIQueryLoader queryLoader;

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	@Value("${customer.type}")
	private String customerType;

	@Value("${customer.category}")
	private String customerCategory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.repository.UPIDataProcessorRepository#
	 * fetchDataFromCBS(com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO)
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<MerchantDetailsCBS> fetchMerchantsFromCBS(UPIBatchExecutionDTO upiBatchExecutionDTO)
			throws UPIRepositoryException {
		LOGGER.info("Fetching new merchants registered between {} and {} from CBS",
				upiBatchExecutionDTO.getFromTimeStamp(), upiBatchExecutionDTO.getToTimeStamp());
		List<MerchantDetailsCBS> merchantDetailsCBS = null;
		try {
			String query = queryLoader.getQuery(UpiConstants.FETCH_MERCHANT_DETAILS_CBS_QUERY);
			merchantDetailsCBS = entityManager.unwrap(Session.class).createNativeQuery(query.trim())
					.setParameter(UpiConstants.FROM_DATE, upiBatchExecutionDTO.getFromTimeStamp())
					.setParameter(UpiConstants.TO_DATE, upiBatchExecutionDTO.getToTimeStamp())
					.setParameter(UpiConstants.CUST_TYPE, customerType)
					.setParameter(UpiConstants.CUST_CATEGORY, customerCategory)
					.setResultTransformer(new UPIBatchResultTransformer()).list();
			LOGGER.info("Found {} merchants registered between {} and {} from CBS", merchantDetailsCBS.size(),
					upiBatchExecutionDTO.getFromTimeStamp(), upiBatchExecutionDTO.getToTimeStamp());
		} catch (Exception e) {
			String reason = "Exception occured while fetching registered merchants from CBS.";
			LOGGER.error(reason);
			throw new UPIRepositoryException(new ExceptionParams(ErrorCodes.UPI_500, reason));
		}
		return merchantDetailsCBS;
	}

}
