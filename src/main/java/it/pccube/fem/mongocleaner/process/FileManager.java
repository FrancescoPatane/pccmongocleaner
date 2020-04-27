package it.pccube.fem.mongocleaner.process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.pccube.fem.mongocleaner.util.Counter;

@Component
public class FileManager {

	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

	private String outcomeFilePath;


	public void printHeadOutcomeFile() {
		try {
			this.createFileOutcomeIfNotExists();
			try (BufferedWriter out = new BufferedWriter(new FileWriter(this.outcomeFilePath, true))){ 
				out.write("#### OUTCOME FOR EXECUTION " + LocalDateTime.now() + " ####" + System.lineSeparator() + System.lineSeparator()); 
			} 
		}catch (IOException e) { 
			logger.error("Exception writing head to outcome file", e.getMessage());
		}
	}

	public void writeOutcomeFile(String lineData, Integer errorCode, String message) {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(this.outcomeFilePath, true))){ 
			out.write(lineData + " - " + errorCode + " - " + message + System.lineSeparator()); 
		}catch (IOException e) { 
			logger.error("Exception writing line to outcome file:" + lineData + " exception: " + e.getMessage());
		}
	}

	public void printOutcomeStats() {
		try {
			try (BufferedWriter out = new BufferedWriter(new FileWriter(this.outcomeFilePath, true))){ 
				out.write(System.lineSeparator() + "#####RECAP#####" + System.lineSeparator()); 
				out.write("Elements in input: " + Counter.totalInputCounter + System.lineSeparator()); 
				out.write("Elements removed: " + Counter.successfullyRemovedCounter + System.lineSeparator()); 
				out.write("Elements not removed: " + Counter.notRemovedCounter + System.lineSeparator()); 
				out.write("####END OF EXECUTION####" + System.lineSeparator()); 
			} 
		}catch (IOException e) { 
			logger.error("Exception writing end statistic to outcome file" + e.getMessage());
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
