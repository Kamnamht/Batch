package com.airtel.merchant.scheduler.stats;

import java.time.LocalDateTime;

/**
 * Singleton class which will cache the job stats.
 * 
 *
 */
public final class UPIJobStats {

	/** The upi job stats. */
	private static UPIJobStats upiJobStats;

	/** The last execution time stamp. */
	private LocalDateTime lastExecutionTimeStamp;

	/** The execution count. */
	private int executionCount;

	/** The is job executing. */
	private boolean isJobExecuting;

	/**
	 * Instantiates a new UPI job stats.
	 */
	private UPIJobStats() {
		lastExecutionTimeStamp = LocalDateTime.MIN;
	}

	/**
	 * Gets the single instance of UPIJobStats.
	 *
	 * @return single instance of UPIJobStats
	 */
	public static UPIJobStats getInstance() {
		if (null == upiJobStats) {
			upiJobStats = new UPIJobStats();
		}
		return upiJobStats;
	}

	/**
	 * Gets the last execution time stamp.
	 *
	 * @return the last execution time stamp
	 */
	public LocalDateTime getLastExecutionTimeStamp() {
		return lastExecutionTimeStamp;
	}

	/**
	 * Sets the last execution time stamp.
	 *
	 * @param lastExecutionTimeStamp
	 *            the new last execution time stamp
	 */
	public void setLastExecutionTimeStamp(LocalDateTime lastExecutionTimeStamp) {
		this.lastExecutionTimeStamp = lastExecutionTimeStamp;
	}

	/**
	 * Gets the execution count.
	 *
	 * @return the execution count
	 */
	public int getExecutionCount() {
		return executionCount;
	}

	/**
	 * Sets the execution count.
	 *
	 * @param executionCount
	 *            the new execution count
	 */
	public void setExecutionCount(int executionCount) {
		this.executionCount = executionCount;
	}

	/**
	 * Checks if is job executing.
	 *
	 * @return true, if is job executing
	 */
	public boolean isJobExecuting() {
		return isJobExecuting;
	}

	/**
	 * Sets the job executing.
	 *
	 * @param isJobExecuting
	 *            the new job executing
	 */
	public void setJobExecuting(boolean isJobExecuting) {
		this.isJobExecuting = isJobExecuting;
	}

}
