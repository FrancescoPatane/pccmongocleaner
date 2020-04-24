package it.pccube.fem.mongocleaner.util;

public class RecordLogger {
	
	public static final String RECORD_ERR_WRITER = "(DISCARD%1$s)|%2$s";
	
	public static final String RECORD_ERR_PROCESSOR = RECORD_ERR_WRITER;
	
	public static final String RECORD_ERR_READER = RECORD_ERR_WRITER;
	
	public static final String RECORD_APP_LOG_READER = "READER%1$s|%2$s";
	
	public static final String RECORD_APP_LOG_DAO = "DBERROR id[%1$s] not saved due to %2$s";
	
	public static final String RECORD_APP_ERR = "(APPERR%1$s)|%2$s|%3$s";
	
	public static final String RECORD_BEHAVIOR_ERR = "(BEHAVIOR)|%1$s|%2$s";
	
	public static final String RECORD_RESTCLIENT_ERR = "(RESTCLI)|%1$s|%2$s";
	
	public static final String RECORD_AUTHSERVICE_ERR = "(AUTHSRV)|%1$s|%2$s";
	
	public static final String RECORD_DOCSERVICE_ERR = "(DOCSRV)|%1$s|%2$s";
	
	public static final String RECORD_SUCCESS_WRITER = "(OK%1$s)|%2$s";

}
