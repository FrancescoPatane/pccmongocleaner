package it.pccube.fem.mongocleaner.process;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pccube.fem.mongocleaner.documentale.DocumentaleService;
import it.pccube.fem.mongocleaner.documentale.OutputDeleteDocumentale;

@Component
public class Processor {

	private static final Logger logger = LoggerFactory.getLogger(Processor.class);


	private String inputFilePath;

	@Autowired
	private DocumentaleService docService;

	@Autowired
	private FileManager fileManager;

	public void processDiscardedFile(String inputFilePath) {
		try (Scanner sc=new Scanner(new FileInputStream(inputFilePath))){  
			while(sc.hasNextLine())  { 
				String lineData = sc.nextLine();
				this.callDelete(lineData);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}



	public String getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}


	public void callDelete(String fileId) {
		logger.info("Deleting file " + fileId);
		try {
			OutputDeleteDocumentale output = docService.deleteFile(fileId);
		}catch(Exception e) {
			logger.error("Error calling delete service for id" + fileId , e.getMessage());
			this.fileManager.writeFailureFile(fileId);
		}

	}
}
