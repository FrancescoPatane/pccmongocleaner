package it.pccube.fem.mongocleaner.process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.pccube.fem.mongocleaner.bean.LineRecordBean;

@Component
public class FileManager {
	
	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
	
	private static final String failureFileName = "failures";
	
	private String failuresFilePath;

	
	@Value("${app.failuresfile.dirpath}")
	private String failuresFileDirPath;
	
	public LineRecordBean readLineData(String line) {
		LineRecordBean data = new LineRecordBean();
		String[] array = line.split("\\|");
		data.setEntity(array[0]);
		data.setErrorDescription(array[1]);
		data.setPrimaryKey(array[2]);
		data.setListId(Arrays.asList(array[3].split(";")));
		return data;
	}
	
	public void createFailureFileForLaunch() throws IOException {
		String dirPath = failuresFileDirPath;
		File directory = new File(dirPath);
		directory.mkdir();
		String filePath = dirPath+File.separator+failureFileName+".txt";
		File file = new File(filePath);
		file.createNewFile();
		this.failuresFilePath = filePath;
	}
	
	public void writeFailureFile(LineRecordBean lineData) {
        try { 
            BufferedWriter out = new BufferedWriter(new FileWriter(this.failuresFilePath, true)); 
            out.write(lineData.toString() + System.lineSeparator()); 
            out.close(); 
        } 
        catch (IOException e) { 
        	logger.error("Exception writing line to failure file:" + e.getMessage());
        } 
	}

}
