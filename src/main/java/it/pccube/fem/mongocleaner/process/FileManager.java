package it.pccube.fem.mongocleaner.process;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileManager {
	
	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
	
	private String failuresFilePath;

	
	public void writeFailureFile(String lineData) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(this.failuresFilePath, true))){ 
            out.write(lineData + System.lineSeparator()); 
            out.close(); 
        } 
        catch (IOException e) { 
        	logger.error("Exception writing line to failure file:" + lineData + "exception: " + e.getMessage());
        }
	}
	
	


	public String getFailuresFilePath() {
		return failuresFilePath;
	}

	public void setFailuresFilePath(String failuresFilePath) {
		this.failuresFilePath = failuresFilePath;
	}
	
}
