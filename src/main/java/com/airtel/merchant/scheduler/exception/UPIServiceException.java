package com.airtel.merchant.scheduler.exception;

import com.airtel.merchant.scheduler.model.ExceptionParams;

/**
 * The Class UPIServiceException will manage all service layer exception.
 */
public class UPIServiceException extends UPISchedulerException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new UPI service exception.
	 *
	 * @param params
	 *            the params
	 * @param cause
	 *            the cause
	 */
	public UPIServiceException(ExceptionParams params, Throwable cause) {
		super(params, cause);
	}

	/**
	 * Instantiates a new UPI service exception.
	 *
	 * @param params
	 *            the params
	 */
	public UPIServiceException(ExceptionParams params) {
		super(params);
	}

}
