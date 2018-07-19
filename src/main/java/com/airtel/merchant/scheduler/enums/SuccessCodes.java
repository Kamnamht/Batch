package com.airtel.merchant.scheduler.enums;

/**
 * The Enum SuccessCodes defines the list all success codes used in the
 * application.
 */
public enum SuccessCodes implements UPIBatchJobStatusCode {

	/** The upi 201. */
	UPI_201;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.enums.UPIBatchJobStatusCode#getStatus()
	 */
	@Override
	public String getStatus() {
		return "Success";
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
