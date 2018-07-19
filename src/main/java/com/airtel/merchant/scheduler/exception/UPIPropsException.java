package com.airtel.merchant.scheduler.exception;

import com.airtel.merchant.scheduler.model.ExceptionParams;

/**
 * The Class UPIPropsException is thrown in case of issues during properties
 * file loading.
 */
public class UPIPropsException extends UPIRuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new UPI props exception.
	 *
	 * @param params
	 *            the params
	 * @param cause
	 *            the cause
	 */
	public UPIPropsException(ExceptionParams params, Throwable cause) {
		super(params, cause);
	}

	/**
	 * Instantiates a new UPI props exception.
	 *
	 * @param params
	 *            the params
	 */
	public UPIPropsException(ExceptionParams params) {
		super(params);
	}

}
