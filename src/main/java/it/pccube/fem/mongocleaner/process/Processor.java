package it.pccube.fem.mongocleaner.process;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.pccube.fem.mongocleaner.bean.LineRecordBean;
import it.pccube.fem.mongocleaner.documentale.DocumentaleService;
import it.pccube.fem.mongocleaner.documentale.OutputDeleteDocumentale;
import it.pccube.fem.mongocleaner.documentale.OutputGetDocumentale;

@Component
public class Processor {
	
	private static final Logger logger = LoggerFactory.getLogger(Processor.class);


	@Value("${app.input.path}")
	private String inputFilePath;
	
	@Autowired
	private DocumentaleService docService;
	
	@Autowired
	private FileManager fileManager;

	public void processDiscardedFile() {
		try  
		{  
			FileInputStream fis=new FileInputStream(inputFilePath);       
			Scanner sc=new Scanner(fis);    
			while(sc.hasNextLine())  {  
				LineRecordBean lineData = this.fileManager.readLineData(sc.nextLine());
				this.deleteDocuments(lineData);
//				this.getDocuments(lineData.getListId());
			}  
			sc.close();     
		}  
		catch(IOException e){  
			e.printStackTrace();  
		}  
	}
	
	

	
	private void deleteDocuments (LineRecordBean lineData) {
		for(String id: lineData.getListId()) {
			try {
				this.callDelete(id);
			}catch(Exception e){
				logger.error("Error calling delete service for line" + lineData.toString() + " id: " + id);
				this.fileManager.writeFailureFile(lineData);
			}
			
		}
	}
	
	private void getDocuments (List<String> listId) {
		for(String id: listId) {
			OutputGetDocumentale output = this.callGet(id);
			logger.info(output.getFilename());
		}
	}
	
	private OutputDeleteDocumentale callDelete(String fileId) {
		logger.info("Deleting file " + fileId);
		OutputDeleteDocumentale output = docService.deleteFile(fileId);
		return output;
	}
	
	private OutputGetDocumentale callGet(String fileId) {
		logger.info("get file " + fileId);
		OutputGetDocumentale output = docService.getFile(fileId);
		return output;
	}
}
