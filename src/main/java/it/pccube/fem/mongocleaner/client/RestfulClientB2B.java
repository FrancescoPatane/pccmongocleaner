package it.pccube.fem.mongocleaner.client;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class RestfulClientB2B {


	private static final Logger logger = LoggerFactory.getLogger(RestfulClientB2B.class);


	private RestTemplate restfulClient = null;
	private ObjectMapper jsonMapper = null;

	@Value("${restfulclient.connectiontimeout:30000}")
	private int restfulclientConnectionTimeout;

	@Value("${restfulclient.readtimeout:30000}")
	private int restfulclientReadTimeout;

	private static final String ERR_MSG = "Errore chiamata esterna";

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


	




	

}
