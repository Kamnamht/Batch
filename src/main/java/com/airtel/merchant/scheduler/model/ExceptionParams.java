package com.airtel.merchant.scheduler.model;

import com.airtel.merchant.scheduler.enums.UPIBatchJobStatusCode;

/**
 * The Class ExceptionParam contains properties of exception encountered.
 */
public class ExceptionParams {

	/** The status code. */
	private UPIBatchJobStatusCode statusCode;

	/** The reason. */
	private String reason;

	/**
	 * Instantiates a new exception params.
	 *
	 * @param statusCode
	 *            the status code
	 * @param reason
	 *            the reason
	 */
	public ExceptionParams(UPIBatchJobStatusCode statusCode, String reason) {
		super();
		this.statusCode = statusCode;
		this.reason = reason;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public UPIBatchJobStatusCode getStatusCode() {
		return statusCode;
	}

	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

}
