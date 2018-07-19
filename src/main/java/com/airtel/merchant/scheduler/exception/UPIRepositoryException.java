package com.airtel.merchant.scheduler.exception;

import com.airtel.merchant.scheduler.model.ExceptionParams;

/**
 * The Class UPIRepositoryException will manage all issues at repository layer.
 */
public class UPIRepositoryException extends UPISchedulerException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new UPI repository exception.
	 *
	 * @param params
	 *            the params
	 * @param cause
	 *            the cause
	 */
	public UPIRepositoryException(ExceptionParams params, Throwable cause) {
		super(params, cause);
	}

	/**
	 * Instantiates a new UPI repository exception.
	 *
	 * @param params
	 *            the params
	 */
	public UPIRepositoryException(ExceptionParams params) {
		super(params);
	}

}
