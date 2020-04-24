package it.pccube.fem.mongocleaner.documentale;

import java.io.Serializable;

public class OutputGetDocumentale implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] file;

	private String filename;

	private String contentType;

	public OutputGetDocumentale() {
		// DEFAULT
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
