package com.airtel.merchant.scheduler.enums;

/**
 * The Interface UPIBatchJobStatusCode will be implemented by Error and Success
 * codes.
 */
public interface UPIBatchJobStatusCode {

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	String getStatus();

	/**
	 * Gets the name of code.
	 *
	 * @return the error code identifier
	 */
	String getIdentifier();

}
