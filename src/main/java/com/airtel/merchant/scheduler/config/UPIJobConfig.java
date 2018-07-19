package com.airtel.merchant.scheduler.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.airtel.merchant.scheduler.job.UPIRegisterationScheduler;

/**
 * The Class UPIJobConfig enlists separate jobs in batch.
 */
@Configuration
@EnableBatchProcessing(modular = true)
public class UPIJobConfig {

	/**
	 * This method registers UPI batch job to application context.
	 *
	 * @return ApplicationContextFactory
	 */
	@Bean
	public ApplicationContextFactory getUPIRegisterationScheduler() {
		return new GenericApplicationContextFactory(UPIRegisterationScheduler.class);
	}

}