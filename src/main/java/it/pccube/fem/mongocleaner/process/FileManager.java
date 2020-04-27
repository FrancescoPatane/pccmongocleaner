package it.pccube.fem.mongocleaner.process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileManager {

	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

	private String outcomeFilePath;


	public void writeOutcomeFile(String lineData, Integer errorCode, String message) {
		try {
			this.createFileOutcomeIfNotExists();
			try (BufferedWriter out = new BufferedWriter(new FileWriter(this.outcomeFilePath, true))){ 
				out.write(lineData + " - " + errorCode + " - " + message + System.lineSeparator()); 
			} 
		}catch (IOException e) { 
			logger.error("Exception writing line to outcome file:" + lineData + " exception: " + e.getMessage());
		}
	}

	private void createFileOutcomeIfNotExists() throws IOException {
		File file = new File(this.outcomeFilePath);
		if(!file.exists()) {
			file.createNewFile();
		}
	}


	public String getOutcomeFilePath() {
		return outcomeFilePath;
	}


	public void setOutcomeFilePath(String outcomeFilePath) {
		this.outcomeFilePath = outcomeFilePath;
	}





}
