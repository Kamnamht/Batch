package com.airtel.merchant.scheduler.exception;

import com.airtel.merchant.scheduler.enums.UPIBatchJobStatusCode;
import com.airtel.merchant.scheduler.model.ExceptionParams;

/**
 * The Class UPISchedulerException will be thrown from Scheduler class.
 */
public class UPISchedulerException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The params. */
	private final transient ExceptionParams params;

	/**
	 * Instantiates a new UPI scheduler exception.
	 *
	 * @param params
	 *            the params
	 * @param cause
	 *            the cause
	 */
	public UPISchedulerException(ExceptionParams params, Throwable cause) {
		super(params.getStatusCode().getIdentifier(), cause);
		this.params = params;
	}

	/**
	 * Instantiates a new UPI scheduler exception.
	 *
	 * @param params
	 *            the params
	 */
	public UPISchedulerException(ExceptionParams params) {
		super();
		this.params = params;
	}

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public ExceptionParams getParams() {
		return params;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public UPIBatchJobStatusCode getStatusCode() {
		return params.getStatusCode();
	}

	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return params.getReason();
	}

	/*
	 * This method append the reason to exception message.
	 * 
	 * @see
	 * com.airtel.merchant.scheduler.exception.UPISchedulerException#getMessage()
	 */
	@Override
	public String getMessage() {
		String finalMessage = "";
		StringBuilder builder = new StringBuilder();
		String superMessage = super.getMessage();
		if (null != superMessage) {
			builder.append(superMessage);
		}
		if (null != getReason()) {
			builder.append(" Reason: ").append(getReason());
			finalMessage = builder.toString();
		}
		return finalMessage;
	}

}
