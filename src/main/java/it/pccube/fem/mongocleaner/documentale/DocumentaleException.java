package it.pccube.fem.mongocleaner.documentale;

public class DocumentaleException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public DocumentaleException(){
		
	}
	
	public DocumentaleException(String message){
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
