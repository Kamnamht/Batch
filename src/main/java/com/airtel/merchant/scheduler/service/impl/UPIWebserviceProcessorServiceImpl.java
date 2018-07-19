package com.airtel.merchant.scheduler.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.airtel.merchant.scheduler.dto.LinkQRRequestDTO;
import com.airtel.merchant.scheduler.dto.LinkQRResponseDTO;
import com.airtel.merchant.scheduler.dto.MerchantDetailDTO;
import com.airtel.merchant.scheduler.dto.MerchantRegistrationAPIRequestDTO;
import com.airtel.merchant.scheduler.dto.MerchantRegistrationAPIResponseDTO;
import com.airtel.merchant.scheduler.entity.MerchantDetails;
import com.airtel.merchant.scheduler.enums.ErrorCodes;
import com.airtel.merchant.scheduler.exception.UPIServiceException;
import com.airtel.merchant.scheduler.helper.UPIBatchHelper;
import com.airtel.merchant.scheduler.model.ExceptionParams;
import com.airtel.merchant.scheduler.model.SendSMSModel;
import com.airtel.merchant.scheduler.service.UPIWebserviceProcessorService;
import com.airtel.merchant.scheduler.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class UPIWebserviceProcessorServiceImpl provides implementation to all
 * base line methods in UPIWebserviceProcessorService.
 */
@Service
public class UPIWebserviceProcessorServiceImpl implements UPIWebserviceProcessorService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UPIWebserviceProcessorServiceImpl.class);

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/** The mapper. */
	@Autowired
	private ObjectMapper mapper;

	/** The Http CC http request factory. */
	@Autowired
	private HttpComponentsClientHttpRequestFactory httpCCHttpRequestFactory;

	/** The vo helper. */
	@Autowired
	private UPIBatchHelper upiBatchHelper;

	/** The mw bulk registration http scheme. */
	@Value("${mw.bulk.registration.ws.http.scheme}")
	private String mwBulkRegistrationHttpScheme;

	/** The mw bulk registration host. */
	@Value("${mw.bulk.registration.ws.host}")
	private String mwBulkRegistrationHost;

	/** The mw bulk registration port. */
	@Value("${mw.bulk.registration.ws.port}")
	private int mwBulkRegistrationPort;

	/** The mw bulk registration URL. */
	@Value("${mw.bulk.registration.ws.url}")
	private String mwBulkRegistrationURL;

	/** The ws request timeout. */
	@Value("${ws.request.timeout}")
	private int wsRequestTimeout;

	/** The sms API http scheme. */
	@Value("${send.sms.ws.http.scheme}")
	private String smsAPIHttpScheme;

	/** The sms API host. */
	@Value("${send.sms.ws.host}")
	private String smsAPIHost;

	/** The sms API port. */
	@Value("${send.sms.ws.port}")
	private int smsAPIPort;

	/** The sms APIURL. */
	@Value("${send.sms.ws.url}")
	private String smsAPIURL;

	/** The link QRAPI http scheme. */
	@Value("${link.qr.ws.http.scheme}")
	private String linkQRAPIHttpScheme;

	/** The link QRAPI host. */
	@Value("${link.qr.ws.host}")
	private String linkQRAPIHost;

	/** The link QRAPI port. */
	@Value("${link.qr.ws.port}")
	private int linkQRAPIPort;

	/** The link QRAPIURL. */
	@Value("${link.qr.ws.url}")
	private String linkQRAPIURL;

	/**
	 * Generates webservice URI.
	 *
	 * @param httpScheme
	 *            the http scheme
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 * @param url
	 *            the url
	 * @return the webservice URI
	 * @throws UPIServiceException
	 *             the UPI service exception
	 */
	public String getWSURI(String httpScheme, String host, int port, String url) throws UPIServiceException {
		return createURI(httpScheme, host, port, url);
	}

	/**
	 * This method will do initial setup for UPIWebserviceProcessorServiceImpl
	 * object.
	 *
	 * @throws KeyManagementException
	 *             the key management exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws KeyStoreException
	 *             the key store exception
	 */
	@PostConstruct
	public void setup() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		LOGGER.debug("Configuring RestTemplate, RequestTimeout, ReadTimeout and Request factory.");

		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();
		@SuppressWarnings("deprecation")
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		httpCCHttpRequestFactory.setReadTimeout(wsRequestTimeout);
		httpCCHttpRequestFactory.setConnectTimeout(wsRequestTimeout);
		httpCCHttpRequestFactory.setHttpClient(httpClient);

		restTemplate.setRequestFactory(httpCCHttpRequestFactory);

	}

	/**
	 * This method will create the bulk registration URI.
	 *
	 * @param httpScheme
	 *            the http scheme
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 * @param url
	 *            the url
	 * @return the string
	 * @throws UPIServiceException
	 *             the UPI service exception
	 */
	private String createURI(String httpScheme, String host, int port, String url) throws UPIServiceException {
		LOGGER.debug("Generating URI for MW bulk registration API.");
		final URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(httpScheme);
		uriBuilder.setHost(host);
		uriBuilder.setPort(port);
		uriBuilder.setPath(url);
		String uri = null;
		try {
			uri = uriBuilder.build().toString();
		} catch (URISyntaxException e) {
			String reason = "Encountered URISyntaxException while generating URI from configured properties";
			LOGGER.error(reason);
			throw new UPIServiceException(new ExceptionParams(ErrorCodes.UPI_500, reason), e.getCause());
		}
		return uri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.service.UPIWebserviceProcessorService#
	 * invokeMWBulkRegistrationAPI(com.airtel.merchant.scheduler.entity.
	 * MerchantDetails)
	 */
	@Override
	public void invokeMWBulkRegistrationAPI(MerchantDetails merchantDetail) throws UPIServiceException {
		MerchantRegistrationAPIResponseDTO apiResponseDTO = null;
		if (!UpiConstants.CBS_FAILURE.equals(merchantDetail.getLinkQRStatus())) {
			String apiUri = getWSURI(mwBulkRegistrationHttpScheme, mwBulkRegistrationHost, mwBulkRegistrationPort,
					mwBulkRegistrationURL);
			LOGGER.info("Registering merchant - id: {} on MWBulk registration API {}", merchantDetail.getCustomerId(),
					apiUri);
			try {
				ResponseEntity<String> response = restTemplate.exchange(apiUri, HttpMethod.POST,
						createRequestEntity(merchantDetail), String.class);
				if (null != response) {
					apiResponseDTO = JsonUtils.jsonToEntity(response.getBody(),
							MerchantRegistrationAPIResponseDTO.class, mapper);
				}
				if (null != apiResponseDTO) {
					LOGGER.info("Merchant registration {} for merchant id: {}", apiResponseDTO.getStatus(),
							merchantDetail.getCustomerId());
				}

				addAPIResponseToMerchantDetail(apiResponseDTO, merchantDetail);
			} catch (ConnectTimeoutException | RestClientException e) {
				merchantDetail.setMerchantRegisterStatus(UpiConstants.TIMED_OUT);
				String reason = "Connection timed out while invoking MW bulk registration API.";
				LOGGER.error(reason);
			} catch (IOException e) {
				StringBuilder reason = new StringBuilder(
						"Error occured while converting response recieved from API to DTO.").append(e.getMessage());
				LOGGER.error(reason.toString());
			}
		}
	}

	/**
	 * Update API status method will add MW bulk registration API response to
	 * MerchantDetail.
	 *
	 * @param apiResponseDTO
	 *            the api response DTO
	 * @param merchantDetail
	 *            the merchant detail
	 */
	private void addAPIResponseToMerchantDetail(MerchantRegistrationAPIResponseDTO apiResponseDTO,
			MerchantDetails merchantDetail) {
		if (null != apiResponseDTO) {
			if (null != apiResponseDTO.getStatus()) {
				merchantDetail.setMerchantRegisterStatus(apiResponseDTO.getStatus());
			}
			merchantDetail.setMerchantRegisterErrorMessage(apiResponseDTO.getErrorMsg());

			MerchantDetailDTO merchantDetailDTO = apiResponseDTO.getMerchantDetail();
			if (null != merchantDetailDTO) {
				merchantDetail.setUniquemid(merchantDetailDTO.getUniquemid());
				merchantDetail.setMerchantPrivateKey(merchantDetailDTO.getMerchantPrivateKey());
				merchantDetail.setMerchantPublicKey(merchantDetailDTO.getMerchantPublicKey());
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.airtel.merchant.scheduler.service.UPIWebserviceProcessorService#sendSMS(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void sendSMS(MerchantDetails merchantDetail) throws UPIServiceException {
		String apiUri = getWSURI(smsAPIHttpScheme, smsAPIHost, smsAPIPort, smsAPIURL);
		LOGGER.info("Sending sms to: {} by invoking send SMS API {}", merchantDetail.getMobile(), apiUri);
		try {
			final SendSMSModel sendSMSBody = upiBatchHelper.prepareSendSMSObject(merchantDetail.getMobile(),
					upiBatchHelper.randomString(15));

			final ResponseEntity<String> responseBody = restTemplate.exchange(apiUri, HttpMethod.POST,
					createRequestEntity(sendSMSBody), String.class);
			if (responseBody != null) {
				String response = responseBody.getBody();
				LOGGER.info("Response from send SMS API: {}", response);
			}
		} catch (final HttpClientErrorException e) {
			String reason = String.format("Failed to send SMS to merchant %s, Reason: %s",
					merchantDetail.getCustomerId(), e.getResponseBodyAsString());
			LOGGER.error(reason);
		} catch (IOException e) {
			String reason = String.format("Failed to send SMS to merchant %s, Reason: %s",
					merchantDetail.getCustomerId(), e.getMessage());
			LOGGER.error(reason);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.airtel.merchant.scheduler.service.UPIWebserviceProcessorService#
	 * invokeLinkQRAPI(com.airtel.merchant.scheduler.entity.MerchantDetails)
	 */
	@Override
	public void invokeLinkQRAPI(MerchantDetails merchantDetail) throws UPIServiceException {
		StringBuilder sb = new StringBuilder();
		String feSessionId = upiBatchHelper.randomString(15);
		String apiUri = sb.append(getWSURI(linkQRAPIHttpScheme, linkQRAPIHost, linkQRAPIPort, linkQRAPIURL))
				.append("?ucId=LINKQR&feSessionId=").append(feSessionId).toString();

		LOGGER.info("Adding QR code for merchant - id: {} on CBS by invoking Link QR API {}",
				merchantDetail.getCustomerId(), apiUri);
		try {
			final ResponseEntity<String> responseBody = restTemplate.exchange(apiUri, HttpMethod.POST,
					createLinkQREntity(merchantDetail, feSessionId), String.class);
			if (responseBody != null) {
				String response = responseBody.getBody();
				StringReader reader = new StringReader(response);
				JAXBContext jaxbContext = JAXBContext.newInstance(LinkQRResponseDTO.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				LinkQRResponseDTO linkQRResponseDTO = (LinkQRResponseDTO) unmarshaller.unmarshal(reader);
				addLinkQRAPIResponse(linkQRResponseDTO, merchantDetail);
				LOGGER.info("Response from Link QR code API: {}", linkQRResponseDTO.getMessageText());
			}
		} catch (final Exception e) {
			final StringBuilder reason = new StringBuilder("Error occured while publishing QR code data to CBS.")
					.append(e.getMessage());
			LOGGER.error(reason.toString());
			merchantDetail.setLinkQRStatus(UpiConstants.CBS_FAILURE);
		}
	}

	/**
	 * Adds the link QRAPI response to merchantDetai.
	 *
	 * @param linkQRResponseDTO
	 *            the link QR response DTO
	 * @param merchantDetail
	 *            the merchant detail
	 */
	private void addLinkQRAPIResponse(LinkQRResponseDTO linkQRResponseDTO, MerchantDetails merchantDetail) {
		if (linkQRResponseDTO.getCode().equals(UpiConstants.ZERO)) {
			LOGGER.info("QR code linked successfully for merchant id: {}", merchantDetail.getCustomerId());
			merchantDetail.setLinkQRStatus(UpiConstants.SUCCESS);
		} else if (linkQRResponseDTO.getCode().equals(UpiConstants.TWO)) {
			LOGGER.error("CBS timedout while linking QR for merchant id: {}, Response: {}",
					merchantDetail.getCustomerId(), linkQRResponseDTO);
			merchantDetail.setLinkQRStatus(UpiConstants.CBS_FAILURE);
		} else {
			LOGGER.error("QR code linking failed for merchant id: {}, Response: {}", merchantDetail.getCustomerId(),
					linkQRResponseDTO);
			merchantDetail.setLinkQRStatus(UpiConstants.FAILURE);
		}

		merchantDetail.setLinkQRErrorCode(linkQRResponseDTO.getErrorCode());
		merchantDetail.setLinkQRMessageText(linkQRResponseDTO.getMessageText());
	}

	/**
	 * Creates the request entity.
	 *
	 * @param sendSMSBody
	 *            the send SMS body
	 * @return the http entity
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private HttpEntity<String> createRequestEntity(SendSMSModel sendSMSBody) throws IOException {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestString = JsonUtils.entityToJson(sendSMSBody, mapper);
		LOGGER.debug("Send SMS API Request body: {}", requestString);
		return new HttpEntity<>(requestString, headers);
	}

	/**
	 * Creates the request entity.
	 *
	 * @param merchantDetail
	 *            the merchant detail
	 * @return the http entity
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private HttpEntity<MerchantRegistrationAPIRequestDTO> createRequestEntity(MerchantDetails merchantDetail)
			throws IOException {
		LOGGER.debug("Creating request entity with body and headers.");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new HttpEntity<>(upiBatchHelper.getMerchantRegistrationAPIRequestDTO(merchantDetail), headers);
	}

	/**
	 * Creates the request entity.
	 *
	 * @param merchantDetail
	 *            the merchant detail
	 * @param feSessionId
	 *            the fe session id
	 * @return the http entity
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private HttpEntity<LinkQRRequestDTO> createLinkQREntity(MerchantDetails merchantDetail, String feSessionId)
			throws IOException {
		LOGGER.debug("Creating request entity with body and headers.");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		return new HttpEntity<>(upiBatchHelper.getLinkQRRequestDTO(merchantDetail, feSessionId), headers);
	}

}
