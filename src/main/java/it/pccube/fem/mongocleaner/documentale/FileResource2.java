package it.pccube.fem.mongocleaner.documentale;

import java.io.File;

import org.springframework.core.io.FileSystemResource;

public class FileResource2 extends FileSystemResource {

	
	/**
	 * @param file
	 */
	public FileResource2(File file) {
		super(file);
		
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
