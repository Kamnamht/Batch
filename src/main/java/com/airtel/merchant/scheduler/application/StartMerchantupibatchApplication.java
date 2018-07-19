package com.airtel.merchant.scheduler.application;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

/**
 * StartMerchantupibatchApplication boots up the batch job, reads the properties
 * file and initiates the UPI processor.
 *
 */
@EnableEncryptableProperties
@ImportResource(value = { "META-INF/spring/application-context.xml" })
@SpringBootApplication
@EnableScheduling
public class StartMerchantupibatchApplication extends SpringBootServletInitializer {

	/**
	 * This method will be invoked while starting up this service.
	 *
	 * @param builder
	 *            the builder
	 * @return the spring application builder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StartMerchantupibatchApplication.class);
	}

	/**
	 * This is the main method that will boot up spring boot application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(StartMerchantupibatchApplication.class, args);
	}

	/**
	 * Primary data source.
	 *
	 * @return the data source
	 */
	@Bean(name = "cbsDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.cbs.datasource")
	public DataSource cbsDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * Cbs entity manager factory.
	 *
	 * @param builder
	 *            the builder
	 * @param cbsDataSource
	 *            the cbs data source
	 * @return the local container entity manager factory bean
	 */
	@Bean(name = "cbsEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean cbsEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("cbsDataSource") DataSource cbsDataSource) {
		return builder.dataSource(cbsDataSource).packages("com.airtel.merchant.model")
				.persistenceUnit("CBSDataProcessorRepositoryImpl").build();
	}

	/**
	 * Cbs transaction manager.
	 *
	 * @param cbsEntityManagerFactory
	 *            the cbs entity manager factory
	 * @return the platform transaction manager
	 */
	@Bean(name = "cbsTransactionManager")
	@Primary
	public PlatformTransactionManager cbsTransactionManager(
			@Qualifier("cbsEntityManagerFactory") EntityManagerFactory cbsEntityManagerFactory) {
		return new JpaTransactionManager(cbsEntityManagerFactory);
	}

	/**
	 * Secondary data source.
	 *
	 * @return the data source
	 */
	@Bean(name = "voltDataSource")
	@ConfigurationProperties(prefix = "spring.volt.datasource")
	public DataSource voltDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * Volt entity manager factory.
	 *
	 * @param builder
	 *            the builder
	 * @param voltDataSource
	 *            the volt data source
	 * @return the local container entity manager factory bean
	 */
	@Bean(name = "voltEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean voltEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("voltDataSource") DataSource voltDataSource) {
		return builder.dataSource(voltDataSource).packages("com.airtel.merchant.scheduler.entity")
				.persistenceUnit("VoltDataProcessorRepositoryImpl").build();
	}

	/**
	 * Volt transaction manager.
	 *
	 * @param voltEntityManagerFactory
	 *            the volt entity manager factory
	 * @return the platform transaction manager
	 */
	@Bean(name = "voltTransactionManager")
	public PlatformTransactionManager voltTransactionManager(
			@Qualifier("voltEntityManagerFactory") EntityManagerFactory voltEntityManagerFactory) {
		return new JpaTransactionManager(voltEntityManagerFactory);
	}

}
