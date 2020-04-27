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
import it.pccube.fem.mongocleaner.util.Counter;

@Component
public class Processor {

	private static final Logger logger = LoggerFactory.getLogger(Processor.class);


	private String inputFilePath;

	@Autowired
	private DocumentaleService docService;

	@Autowired
	private FileManager fileManager;

	public void processDiscardedFile() {
		this.fileManager.printHeadOutcomeFile();
		try (Scanner sc=new Scanner(new FileInputStream(inputFilePath))){  
			while(sc.hasNextLine())  { 
				Counter.totalInputCounter++;
				String lineData = sc.nextLine();
				this.callDelete(lineData);
			}
			this.fileManager.printOutcomeStats();
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
			if(output.getErrorCode() == 200) {
				logger.info("File " + fileId + " successfully removed");
				Counter.successfullyRemovedCounter++;
			}else{
				Counter.notRemovedCounter++;
				logger.error("Error calling delete service for id" + fileId , "error code: " + output.getErrorCode() + " message: " + output.getEsito());
			}
			this.fileManager.writeOutcomeFile(fileId, output.getErrorCode(), output.getEsito());
		}catch(Exception e) {
			logger.error("Error calling delete service for id" + fileId , e.getMessage());
			Counter.notRemovedCounter++;
			this.fileManager.writeOutcomeFile(fileId, null, e.getMessage());
		}

	}
}
