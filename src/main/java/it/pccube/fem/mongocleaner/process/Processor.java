package it.pccube.fem.mongocleaner.process;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.pccube.fem.mongocleaner.bean.LineRecordBean;

@Component
public class Processor {

	@Value("${app.input.path}")
	private String inputFilePath;

	public void processDiscardedFile() {
		try  
		{  
			FileInputStream fis=new FileInputStream(inputFilePath);       
			Scanner sc=new Scanner(fis);    
			while(sc.hasNextLine())  {  
				LineRecordBean lineData = this.readLineData(sc.nextLine());
				System.out.println(lineData.toString());
			}  
			sc.close();     
		}  
		catch(IOException e){  
			e.printStackTrace();  
		}  
	}
	
	
	private LineRecordBean readLineData(String line) {
		LineRecordBean data = new LineRecordBean();
		String[] array = line.split("|");
		data.setEntity(array[0]);
		data.setErrorDescription(array[1]);
		data.setPrimaryKey(array[2]);
		data.setListId(Arrays.asList(array[3].split(";")));
		return data;
	}

}
