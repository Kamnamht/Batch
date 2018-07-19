package com.airtel.merchant.scheduler.dto;

import java.time.LocalDateTime;

/**
 * This class contains properties required for batch execution.
 *
 */
public class UPIBatchExecutionDTO {

	/** The from time stamp. */
	private LocalDateTime fromTimeStamp;

	/** The to time stamp. */
	private LocalDateTime toTimeStamp;

	/**
	 * Gets the from time stamp.
	 *
	 * @return the from time stamp
	 */
	public LocalDateTime getFromTimeStamp() {
		return fromTimeStamp;
	}

	/**
	 * Sets the from time stamp.
	 *
	 * @param fromTimeStamp
	 *            the new from time stamp
	 */
	public void setFromTimeStamp(LocalDateTime fromTimeStamp) {
		this.fromTimeStamp = fromTimeStamp;
	}

	/**
	 * Gets the to time stamp.
	 *
	 * @return the to time stamp
	 */
	public LocalDateTime getToTimeStamp() {
		return toTimeStamp;
	}

	/**
	 * Sets the to time stamp.
	 *
	 * @param toTimeStamp
	 *            the new to time stamp
	 */
	public void setToTimeStamp(LocalDateTime toTimeStamp) {
		this.toTimeStamp = toTimeStamp;
	}

}
