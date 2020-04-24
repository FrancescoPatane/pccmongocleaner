package it.pccube.fem.mongocleaner.client;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.pccube.fem.mongocleaner.documentale.OutputDeleteDocumentale;
import it.pccube.fem.mongocleaner.util.RecordLogger;





@Service
public class RestfulClientB2B {


	private static final Logger logger = LoggerFactory.getLogger(RestfulClientB2B.class);


	private RestTemplate restfulClient = null;
	private ObjectMapper jsonMapper = null;

	@Value("${restfulclient.connectiontimeout:30000}")
	private int restfulclientConnectionTimeout;

	@Value("${restfulclient.readtimeout:30000}")
	private int restfulclientReadTimeout;


	@PostConstruct
	private void init() {
		SimpleClientHttpRequestFactory schrf = new SimpleClientHttpRequestFactory();
		schrf.setBufferRequestBody(false);
		schrf.setConnectTimeout(restfulclientConnectionTimeout);
		schrf.setReadTimeout(restfulclientReadTimeout);
		this.restfulClient = new RestTemplate(schrf);
		this.restfulClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		for (HttpMessageConverter<?> converter : restfulClient.getMessageConverters()) {
			if (converter instanceof StringHttpMessageConverter) {
				((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
			}
		}

		this.jsonMapper = new ObjectMapper();
		this.jsonMapper.enable(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		this.jsonMapper
		.disable(com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		this.jsonMapper.setSerializationInclusion(Include.NON_NULL);

		logger.info("restful client successfully initialized");
	}
	
	
	public ResponseEntity<byte[]> doGetDownload(String url, HttpHeaders headers) {
		ResponseEntity<byte[]> response = this.restfulClient.exchange(url, HttpMethod.GET,
				new HttpEntity<>(null, headers), byte[].class);
		if (!HttpStatus.OK.equals(response.getStatusCode())) {
			logger.error(RecordLogger.RECORD_RESTCLIENT_ERR, url);
		}
		return response;
	}
	
	public ResponseEntity<OutputDeleteDocumentale> doGetDelete(String url, HttpHeaders headers) {
		ResponseEntity<OutputDeleteDocumentale> response = this.restfulClient.exchange(url, HttpMethod.GET,
				new HttpEntity<>(null, headers), OutputDeleteDocumentale.class);
		if (!HttpStatus.OK.equals(response.getStatusCode())) {
			logger.error(RecordLogger.RECORD_RESTCLIENT_ERR, url);
		}
		return response;
	}


	




	

}
