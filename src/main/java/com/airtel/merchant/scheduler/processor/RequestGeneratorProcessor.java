package com.airtel.merchant.scheduler.processor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.dto.UPIBatchExecutionDTO;
import com.airtel.merchant.scheduler.exception.UPIServiceException;
import com.airtel.merchant.scheduler.service.UPIDataProcessorService;
import com.airtel.merchant.scheduler.stats.UPIJobStats;

/**
 * 
 * RequestGeneratorProcessor will generate request DTO which will be further
 * propagated to other processors.
 *
 */
@Component
public class RequestGeneratorProcessor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestGeneratorProcessor.class);

	/** The upi initiation time service. */
	@Autowired
	private UPIDataProcessorService upiDataProcessorService;

	/** The props from time stamp. */
	@Value("${upi.batch.pick.merchants.fromTimeStamp}")
	private String propsFromTimeStamp;

	/** The over ride volt time stamp. */
	@Value("${upi.batch.override.volt.fromTimeStamp}")
	private boolean overRideVoltTimeStamp;

	/**
	 * This method will generate UPIBatchExecutionDTO based on the parameters passed
	 * to it.
	 *
	 * @param batchJobInitiationTimeStamp
	 *            time at which batch job is initiated.
	 * @return UPIBatchExecutionDTO
	 */
	public UPIBatchExecutionDTO generateUPIBatchExecutionDTO(LocalDateTime batchJobInitiationTimeStamp) {
		LOGGER.debug("Generating UPIBatchExecutionDTO");

		UPIBatchExecutionDTO upiBatchExecutionDTO = new UPIBatchExecutionDTO();
		LocalDateTime cachedFromTimeStamp = UPIJobStats.getInstance().getLastExecutionTimeStamp();

		if (null != cachedFromTimeStamp && cachedFromTimeStamp != LocalDateTime.MIN) {
			upiBatchExecutionDTO.setFromTimeStamp(cachedFromTimeStamp.plusNanos(1));
		} else if (overRideVoltTimeStamp && !StringUtils.isEmpty(propsFromTimeStamp)) {
			upiBatchExecutionDTO.setFromTimeStamp(getFromTimeStampProperties());
		} else {
			fetchAndSetFromTimeStamp(upiBatchExecutionDTO);
		}
		upiBatchExecutionDTO.setToTimeStamp(batchJobInitiationTimeStamp);
		LOGGER.info("Batch job will pick up merchants registered between {} and {}",
				upiBatchExecutionDTO.getFromTimeStamp(), upiBatchExecutionDTO.getToTimeStamp());
		return upiBatchExecutionDTO;
	}

	/**
	 * Gets the from time stamp properties.
	 *
	 * @return the from time stamp properties
	 */
	private LocalDateTime getFromTimeStampProperties() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(UpiConstants.YYYY_MM_DD_HH_MM_SS);
		LocalDateTime propsTimeStamp = null;
		try {
			propsTimeStamp = LocalDateTime.parse(propsFromTimeStamp, dateFormat);
		} catch (Exception e) {
			LOGGER.warn(
					"Error occured while parsing batch job initiation timestamp from properties file, falling back to today's date");
			propsTimeStamp = LocalDate.now().atStartOfDay();
		}
		if (null == propsTimeStamp) {
			LOGGER.warn("Configured timestmp {} is after batch start time, falling back to today's date",
					propsTimeStamp);
			propsTimeStamp = LocalDate.now().atStartOfDay();
		}
		return propsTimeStamp;
	}

	/**
	 * This method will fetch fromTimeStamp from Volt DB.
	 *
	 * @param upiBatchExecutionDTO
	 *            the upi batch execution DTO
	 */
	private void fetchAndSetFromTimeStamp(UPIBatchExecutionDTO upiBatchExecutionDTO) {
		LocalDateTime fromTimeStampDB;
		try {
			fromTimeStampDB = upiDataProcessorService.getInitiationTimeStamp();
		} catch (UPIServiceException e) {
			LOGGER.error("Error encountered while fetching fromTimeStamp from DB, falling back to today's date.");
			fromTimeStampDB = LocalDate.now().atStartOfDay();
		}
		if (null == fromTimeStampDB) {
			LOGGER.warn("FromTimeStamp fetched from DB is either null, falling back to today's date.");
			fromTimeStampDB = LocalDate.now().atStartOfDay();
		}
		upiBatchExecutionDTO.setFromTimeStamp(fromTimeStampDB.plusNanos(1));
	}

}
