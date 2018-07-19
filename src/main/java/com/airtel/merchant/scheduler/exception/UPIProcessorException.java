package com.airtel.merchant.scheduler.exception;

import com.airtel.merchant.scheduler.model.ExceptionParams;

/**
 * The Class UPIProcessorException will be throw at processor level.
 */
public class UPIProcessorException extends UPISchedulerException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new UPI processor exception.
	 *
	 * @param params
	 *            the params
	 * @param cause
	 *            the cause
	 */
	public UPIProcessorException(ExceptionParams params, Throwable cause) {
		super(params, cause);
	}

	/**
	 * Instantiates a new UPI processor exception.
	 *
	 * @param params
	 *            the params
	 */
	public UPIProcessorException(ExceptionParams params) {
		super(params);
	}

}
