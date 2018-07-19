package com.airtel.merchant.scheduler.model;

import java.util.List;

/**
 * The Class LoggerModel contains properties required for logging.
 */
public class LoggerModel {

	/** The start time. */
	private long startTime;

	/** The merchant id. */
	private String merchantId;

	/** The errors. */
	private List<LoggerError> errors;

	/** The methods. */
	private List<MethodStats> methods;

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime
	 *            the new start time
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the merchant id.
	 *
	 * @return the merchant id
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * Sets the merchant id.
	 *
	 * @param merchantId
	 *            the new merchant id
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public List<LoggerError> getErrors() {
		return errors;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors
	 *            the new errors
	 */
	public void setErrors(List<LoggerError> errors) {
		this.errors = errors;
	}

	/**
	 * Gets the methods.
	 *
	 * @return the methods
	 */
	public List<MethodStats> getMethods() {
		return methods;
	}

	/**
	 * Sets the methods.
	 *
	 * @param methods
	 *            the new methods
	 */
	public void setMethods(List<MethodStats> methods) {
		this.methods = methods;
	}

	/**
	 * The Class LoggerError.
	 */
	public static class LoggerError {

		/** The error code. */
		private String errorCode;

		/** The error description. */
		private String errorDescription;

		/**
		 * Instantiates a new logger error.
		 */
		public LoggerError() {

		}

		/**
		 * Instantiates a new logger error.
		 *
		 * @param errorCode
		 *            the error code
		 * @param errorDescription
		 *            the error description
		 */
		public LoggerError(String errorCode, String errorDescription) {
			this.errorCode = errorCode;
			this.errorDescription = errorDescription;
		}

		/**
		 * Gets the error code.
		 *
		 * @return the error code
		 */
		public String getErrorCode() {
			return errorCode;
		}

		/**
		 * Sets the error code.
		 *
		 * @param errorCode
		 *            the new error code
		 */
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		/**
		 * Gets the error description.
		 *
		 * @return the error description
		 */
		public String getErrorDescription() {
			return errorDescription;
		}

		/**
		 * Sets the error description.
		 *
		 * @param errorDescription
		 *            the new error description
		 */
		public void setErrorDescription(String errorDescription) {
			this.errorDescription = errorDescription;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return " errorCode " + errorCode + " errorDescription " + errorDescription + " ";
		}
	}

	/**
	 * The Class MethodStats.
	 */
	public static class MethodStats {

		/** The method name. */
		private String methodName;

		/** The method time. */
		private long methodTime;

		/**
		 * Gets the method name.
		 *
		 * @return the method name
		 */
		public String getMethodName() {
			return methodName;
		}

		/**
		 * Sets the method name.
		 *
		 * @param methodName
		 *            the new method name
		 */
		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		/**
		 * Gets the method time.
		 *
		 * @return the method time
		 */
		public long getMethodTime() {
			return methodTime;
		}

		/**
		 * Sets the method time.
		 *
		 * @param methodTime
		 *            the new method time
		 */
		public void setMethodTime(long methodTime) {
			this.methodTime = methodTime;
		}

		/**
		 * Instantiates a new method stats.
		 *
		 * @param methodName
		 *            the method name
		 * @param methodTime
		 *            the method time
		 */
		public MethodStats(String methodName, long methodTime) {
			this.methodName = methodName;
			this.methodTime = methodTime;
		}

	}

}
