package com.airtel.merchant.scheduler.enums;

/**
 * The Enum ErrorCodes defines the list of all error codes used in application.
 */
public enum ErrorCodes implements UPIBatchJobStatusCode {

	/** The upi 500. */
	UPI_500;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.enums.UPIBatchJobStatusCode#getStatus()
	 */
	@Override
	public String getStatus() {
		return "Internal Server Error";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.airtel.merchant.scheduler.enums.UPIBatchJobStatusCode#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return name();
	}

}
