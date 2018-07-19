package com.airtel.merchant.scheduler.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.airtel.merchant.scheduler.config.Config.MerchantTradeValues;
import com.airtel.merchant.scheduler.config.Config.MerchantTradeValues.MerchantTradeValue5.MerchantTradeValue6;
import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.enums.ErrorCodes;
import com.airtel.merchant.scheduler.exception.UPIPropsException;
import com.airtel.merchant.scheduler.model.ExceptionParams;

/**
 * MCCConfigLoader will load MCC configurations and cache them into java
 * objects.
 */
@Component
public class MCCConfigLoader {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MCCConfigLoader.class);

	/** The mcc config map. */
	private final Map<String, List<MerchantTradeValue6>> mccConfigMap = new HashMap<>();

	/** The mcc config file path. */
	@Value("${mcc.config.file.path}")
	private String mccConfigFilePath;

	/**
	 * This method will return unmodifiable mccConfigMap.
	 *
	 * @return the mcc config map
	 */
	public Map<String, List<MerchantTradeValue6>> getMccConfigMap() {
		return Collections.unmodifiableMap(mccConfigMap);
	}

	/**
	 * This method checks and reads the mccs config file, parse it and put it to
	 * Map.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws UPIPropsException
	 *             the UPI props exception
	 */
	@PostConstruct
	public void loadRights() throws IOException {
		LOGGER.info("Reading MCC-Code config file and caching it.");
		if (StringUtils.isNotBlank(mccConfigFilePath)) {

			File file = new File(mccConfigFilePath);
			if (file.exists()) {

				Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
				unmarshaller.setPackagesToScan(UpiConstants.MCC_CONFIG_PACKAGE);

				try (FileInputStream fis = new FileInputStream(file);) {

					Config config = (Config) unmarshaller.unmarshal(new StreamSource(fis));
					extractRightsConfig(config);
				} catch (XmlMappingException | IOException ex) {
					String reason = "Terminating application boot, Error while parsing MCC config file";
					LOGGER.error(reason);
					throw new UPIPropsException(new ExceptionParams(ErrorCodes.UPI_500, reason), ex.getCause());
				}

			} else {

				String reason = String.format(
						"Terminating application boot, Not able to load configurations, MCC configuration file not found on path %s",
						mccConfigFilePath);
				LOGGER.error(reason);
				throw new UPIPropsException(new ExceptionParams(ErrorCodes.UPI_500, reason));
			}
		} else {
			String reason = "Terminating application boot, Not able to load configuration, incorrect path to MCC configuration file";
			LOGGER.error(reason);
			throw new UPIPropsException(new ExceptionParams(ErrorCodes.UPI_500, reason));
		}
		LOGGER.debug("MCC-Code config file successfully cached.");
	}

	/**
	 * This method will extract details from config object and insert into
	 * mccConfigMap.
	 *
	 * @param config
	 *            - config object.
	 */
	private void extractRightsConfig(Config config) {
		MerchantTradeValues merchantTradeValues = config.getMerchantTradeValues();
		merchantTradeValues.getMerchantTradeValue5().forEach(merchantTradeValue5 -> mccConfigMap
				.put(merchantTradeValue5.getId(), merchantTradeValue5.getMerchantTradeValue6()));
	}

}
