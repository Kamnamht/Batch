package com.airtel.merchant.scheduler.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

/**
 * The Class UPIFileUtil will define helper methods for all file related
 * operations.
 */
@Component
public class UPIFileUtil {

	/**
	 * Instantiates a new UPI file util.
	 */
	private UPIFileUtil() {
	}

	/**
	 * This method will read the file from file system and returns the contents as
	 * String.
	 *
	 * @param fileName
	 *            the file name
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String readFile(String fileName) throws IOException {
		String data = null;
		try (InputStream fis = UPIFileUtil.class.getResourceAsStream(fileName)) {
			byte[] fisBytes = IOUtils.toByteArray(fis);
			data = new String(fisBytes);
		}
		return data;
	}

}
