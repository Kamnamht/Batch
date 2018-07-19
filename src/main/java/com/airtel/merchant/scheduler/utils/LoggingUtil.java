package com.airtel.merchant.scheduler.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.airtel.merchant.scheduler.model.LoggerModel;

/**
 * The Class LoggingUtil provides helper methods for log generation to 3rd party
 * systems.
 */
@Component
public class LoggingUtil {

	/** The merchant API service id. */
	@Value("${logging.merchantAPIServiceId}")
	private String merchantAPIServiceId;

	/**
	 * Convert logger model to string.
	 *
	 * @param loggerModel
	 *            the logger model
	 * @return the string
	 */
	public String convertLoggerModelToString(LoggerModel loggerModel) {
		StringBuilder builder = new StringBuilder();
		int errorCode = 0;
		builder.append(merchantAPIServiceId + " | ");

		if (!CollectionUtils.isEmpty(loggerModel.getErrors())) {
			errorCode = 1;
		}
		builder.append(errorCode + " | ");
		builder.append(loggerModel.getMerchantId() + " | ");

		if (errorCode == 1 && !CollectionUtils.isEmpty(loggerModel.getErrors())) {
			for (int i = 0; i < loggerModel.getErrors().size(); i++) {
				if (i != 0)
					builder.append("-");
				builder.append(loggerModel.getErrors().get(i).getErrorCode() + ","
						+ loggerModel.getErrors().get(i).getErrorDescription());
			}
		}

		long endTime = System.currentTimeMillis();
		long time = (endTime - loggerModel.getStartTime());

		builder.append(" | ");
		builder.append(time);
		return builder.toString();
	}
}
