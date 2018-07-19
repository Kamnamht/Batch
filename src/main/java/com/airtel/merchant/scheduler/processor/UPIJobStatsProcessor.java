package com.airtel.merchant.scheduler.processor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.stats.UPIJobStats;

/**
 * This class will provide methods to collect job stats on startup and cleanup.
 * 
 */
@Component
public class UPIJobStatsProcessor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIJobStatsProcessor.class);

	/** The upi job stats. */
	private UPIJobStats upiJobStats = UPIJobStats.getInstance();

	/**
	 * Adds the job stats on startup.
	 *
	 * @param currentTimeStamp
	 *            the current time stamp
	 */
	public void addJobStatsOnStartup(LocalDateTime currentTimeStamp) {
		upiJobStats.setJobExecuting(Boolean.TRUE);
		upiJobStats.setExecutionCount(calculateExecutionCount(currentTimeStamp));
		LOGGER.info("UPI bulk registration scheduler batch initiated at: {}, execution count: {}", currentTimeStamp,
				upiJobStats.getExecutionCount());
	}

	/**
	 * Update job stats on termination.
	 */
	public void updateJobStatsOnTermination() {
		LOGGER.debug("Updating job stats on job termination.");
		upiJobStats.setJobExecuting(Boolean.FALSE);
	}

	/**
	 * Update job stats on success.
	 *
	 * @param merchantDetails
	 *            the merchant details
	 */
	public void updateOnSuccess(List<MerchantDetails> merchantDetails) {
		LOGGER.debug("Updating UPI bulk registration job stats if job ran successfully.");
		Optional<LocalDateTime> recordsProcessedTill = merchantDetails.stream().map(MerchantDetails::getActivationDate)
				.max(LocalDateTime::compareTo);
		if (recordsProcessedTill.isPresent()) {
			upiJobStats.setLastExecutionTimeStamp(recordsProcessedTill.get());
		}
	}

	/**
	 * Calculate execution count.
	 *
	 * @param currentTimeStam
	 *            the current time stam
	 * @return the int
	 */
	private int calculateExecutionCount(LocalDateTime currentTimeStam) {
		LOGGER.debug("Calculating UPI bulk registration job execution count.");
		int count = 1;
		Duration timeDiff = Duration.between(currentTimeStam, upiJobStats.getLastExecutionTimeStamp());
		long diff = Math.abs(timeDiff.toDays());
		if (diff < 1) {
			count = upiJobStats.getExecutionCount() + 1;
		}
		return count;
	}

}
