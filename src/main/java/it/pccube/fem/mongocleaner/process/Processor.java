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

	public void processDiscardedFile() {
		try  
		{  
			FileInputStream fis=new FileInputStream(inputFilePath);       
			Scanner sc=new Scanner(fis);    
			while(sc.hasNextLine())  {  
				LineRecordBean lineData = this.readLineData(sc.nextLine());
//				this.deleteDocuments(lineData.getListId());
				this.getDocuments(lineData.getListId());
			}  
			sc.close();     
		}  
		catch(IOException e){  
			e.printStackTrace();  
		}  
	}
	
	
	private LineRecordBean readLineData(String line) {
		LineRecordBean data = new LineRecordBean();
		String[] array = line.split("\\|");
		data.setEntity(array[0]);
		data.setErrorDescription(array[1]);
		data.setPrimaryKey(array[2]);
		data.setListId(Arrays.asList(array[3].split(";")));
		return data;
	}
	
	private void deleteDocuments (List<String> listId) {
		for(String id: listId) {
			this.callDelete(id);
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
