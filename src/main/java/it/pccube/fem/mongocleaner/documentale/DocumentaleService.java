package it.pccube.fem.mongocleaner.documentale;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.mail.internet.ContentDisposition;
import javax.mail.internet.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.pccube.fem.mongocleaner.client.RestfulClientB2B;




@Service
public class DocumentaleService {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentaleService.class);
	
	@Value("${clusters.documentale.ibm}")
	private String baseUrl;
	
	private static final String URL_GET_FILE = "/file";

	private static final String URL_REMOVE_FILE = "/remove";
	
	private static final String ERR_MSG = "Errore chiamata documentale";


	@Autowired
	private RestfulClientB2B restfulClient;



	public OutputGetDocumentale getFile(String id) {
		logger.info("Download documento");
		ResponseEntity<byte[]> response = restfulClient.doGetDownload(baseUrl+URL_GET_FILE + "/" + id, new HttpHeaders());
		try {
			ContentDisposition contentDisposition = new ContentDisposition(
					response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
			OutputGetDocumentale result = new OutputGetDocumentale();
			result.setFile(response.getBody());
			result.setFilename(contentDisposition.getParameter("filename"));
			result.setContentType(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE));
			return result;
		} catch (ParseException e) {
			throw new DocumentaleException(ERR_MSG);
		}
	}

	public OutputDeleteDocumentale deleteFile(String id) {
		logger.info("Delete documento " + id);
		ResponseEntity<OutputDeleteDocumentale> response = restfulClient.doGetDelete(baseUrl+URL_REMOVE_FILE + "/" + id,
				new HttpHeaders());
		OutputDeleteDocumentale result = response.getBody();
		return result;
	}

}
