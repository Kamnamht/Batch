package com.airtel.merchant.scheduler.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.enums.ErrorCodes;
import com.airtel.merchant.scheduler.exception.UPIPropsException;
import com.airtel.merchant.scheduler.model.ExceptionParams;
import com.airtel.merchant.scheduler.utils.UPIFileUtil;

/**
 * The Class UPIQueryLoader will provide methods to load all query files and
 * cache them to be fetched by repositories.
 */
@Component
public class UPIQueryLoader {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIQueryLoader.class);

	/** The query map. */
	private final Map<String, String> queryMap = new HashMap<>();

	/** The fetch from time query. */
	@Value("${upi.batch.fromtimeStamp.query}")
	private String fetchFromTimeQuery;

	/** The fetch merchant details CBS. */
	@Value("${upi.batch.merchant.details.cbs.query}")
	private String fetchMerchantDetailsCBS;

	/** The fetch merchant details volt. */
	@Value("${upi.batch.merchant.details.volt.query}")
	private String fetchMerchantDetailsVolt;

	/**
	 * This method will load all query files and cache them.
	 */
	@PostConstruct
	public void loadQueryFiles() {
		String fromTimeStampQuery = null;
		try {
			LOGGER.debug("Loading fetchFromTimeStamp file and adding to to cache");
			fromTimeStampQuery = UPIFileUtil.readFile(fetchFromTimeQuery);
		} catch (Exception e) {
			String reason = "Error occured while reading fetch from timestamp query file";
			LOGGER.error(reason);
			throw new UPIPropsException(new ExceptionParams(ErrorCodes.UPI_500, reason), e.getCause());
		}
		queryMap.put(UpiConstants.FETCH_FROM_TIMESTAMP_QUERY, fromTimeStampQuery);
		String fetchMerchantDetailsFromCBSQuery = null;
		try {
			LOGGER.debug("Loading fetchMerchantDetailsFromCBSQuery file and adding to to cache");
			fetchMerchantDetailsFromCBSQuery = UPIFileUtil.readFile(fetchMerchantDetailsCBS);
		} catch (Exception e) {
			String reason = "Error occured while reading merchant details query file from CBS";
			LOGGER.error(reason);
			throw new UPIPropsException(new ExceptionParams(ErrorCodes.UPI_500, reason), e.getCause());
		}
		queryMap.put(UpiConstants.FETCH_MERCHANT_DETAILS_CBS_QUERY, fetchMerchantDetailsFromCBSQuery);

		String fetchMerchantDetailsFromVOLTQuery = null;
		try {
			LOGGER.debug("Loading fetchMerchantDetailsFromVOLTQuery file and adding to to cache");
			fetchMerchantDetailsFromVOLTQuery = UPIFileUtil.readFile(fetchMerchantDetailsVolt);
		} catch (Exception e) {
			String reason = "Error occured while reading merchant details query file from Volt";
			LOGGER.error(reason);
			throw new UPIPropsException(new ExceptionParams(ErrorCodes.UPI_500, reason), e.getCause());
		}
		queryMap.put(UpiConstants.FETCH_MERCHANT_DETAILS_VOLT_QUERY, fetchMerchantDetailsFromVOLTQuery);
	}

	/**
	 * This method will get the query string from query map.
	 *
	 * @param identifier
	 *            the identifier
	 * @return the query
	 */
	public String getQuery(String identifier) {
		return queryMap.get(identifier);
	}

}
