package it.pccube.fem.mongocleaner.documentale;

import org.springframework.core.io.ByteArrayResource;

public class FileResource extends ByteArrayResource {

	public FileResource(byte[] byteArray) {
		super(byteArray);
	}

	private String filename;

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String getFilename() {
		return filename;
	}
	
}
