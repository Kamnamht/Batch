MERCHANT UPI BATCH
=============== 

## About
Merchant UPI batch application is a scheduled task, which will run periodically and fetch registered merchants from CBS, generate closed loop QR and UPI QR string if not present and invoke the mobileware API to register UPI string of merchant to enable it for UPI transaction's.

### Prerequisite to run
    1. JDK - 8 

### Build instructions
	git clone http://{OLM-ID}@code.airtelworld.in:7990/bitbucket/scm/air/merchantupibatch.git
	
	cd merchantupibatch/
	mvn clean install
	
### Run UPI batch
Externalize mccMappingConfig.xml and application.properties to a directory, move to target folder under /merchantand hit the below command:
 java -jar merchantupibatch-1.0.0.jar which will consume internal applicaition.properties file.
 java -Dspring.config.location=/Users/*****/applicaition.properties -jar merchantupibatch-1.0.0.jar for external configuration file.

### Getting Started - Configuration.
Before starting batch job, create a table in VOLT database using addMerchantTable_Volt.sql present inside /Merchantupibatch/sql directory. 
This table will contain the stats of records processed by batch job.

We need to configure all configurations in application.properties file, which can be externalize, details of configurations present below:

### Configurations

  1. "server.port"  Port on which application will run.
  2. "job.upi.cron-time" Pattern to configure batch job initiation schedule.
  3. "logging.path" Log path
  4. "logging.merchantAPIServiceId" Service id to be printed in logs.
  5. "upi.batch.pick.merchants.fromTimeStamp" Time from which batch will pick registered merchants, shouldn't be greater than current date time.
  6. CBS database configurations
       a. spring.cbs.datasource.jdbc-url
       b. spring.cbs.datasource.username
       c. spring.cbs.datasource.password
       d. spring.cbs.datasource.driver-class-name
  7. VOLT database configurations
       a. spring.volt.datasource.jdbc-url
       b. spring.volt.datasource.username
       c. spring.volt.datasource.password
       d. spring.volt.datasource.driver-class-name
  8. jasypt.encryptor.password Encryption key for CBS and VOLT passwords.
  9. spring.main.banner-mode sample value Show/Hide spring banner while application boot.
 10. Hikari datasource configuration:
       a. spring.datasource.hikari.connection-timeout Time till which application will wait for DB connection.
       b. spring.datasource.hikari.maximum-pool-size
 11. Hibernate static configurations:
       a. spring.jpa.show-sql sample value Enable/Disable sql in logs.
       b. spring.jpa.properties.hibernate.format_sql
 12. Use ../config/mccMappingFile.xml if you are running application via terminal or config/mccMappingFile.xml if you are running application as Spring boot.mcc.config.file.path sample value --   config/mccMappingFile.xml
       a. upi.batch.fromtimeStamp.query
       b. upi.batch.merchant.details.cbs.query
       c. upi.batch.merchant.details.volt.query
  13. Static values to be sent to Mobile ware API for merchant registration.
       a. mcc.config.file.callback.url sample value -- http://samplacallbackurl.com
       b. mcc.config.file.sample.email.domain sample value -- @domain.com
  14. MW Bulk registration API details
       a. mw.bulk.registration.ws.http.scheme
       b. mw.bulk.registration.ws.host
       c. mw.bulk.registration.ws.port
       d. mw.bulk.registration.ws.url
       e. ws.request.timeout
       f. mw.bulk.registration.ws.forkjoin.poolsize
  15. Send SMS API details
       a. send.sms.ws.http.scheme
       b. send.sms.ws.host
       c. send.sms.ws.port
       d. send.sms.ws.url
  16. Link QR API details
       a. link.qr.ws.http.scheme
       b. link.qr.ws.host
       c. link.qr.ws.port
       d. link.qr.ws.url
  17. SMS Config
       a. send.sms.ws.text.message sample value -- Dear Airtel Payment Bank Merchant , You have now been enabled to receive UPI payments. Transactions > 2000 Rs will be charged @ 0.65% / transaction.
       b. send.sms.ws.sender.name sample value -- 911234567890

  18. Encryption Key, change this on production.
       a. qr.string.encryption.key sample value -- Welcome To Airtel payments Bank Services
  19. Values to generate UPI
       a. qr.code.uri sample value -- https://www.airtelbank.in/bank/qrscan?
       b. merchant.upi.qr.uri sample value -- upi://pay?
       c. merchant.upi.qr.handle sample value -- @mairtel
       d. merchant.upi.tn.narration sample value -- Payment made to Merchant
  20. CBS data filtering keys
       a. customer.type sample value -- MER
       b. customer.category sample value -- 30186




