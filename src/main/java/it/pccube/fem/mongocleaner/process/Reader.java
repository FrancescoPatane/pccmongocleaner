package it.pccube.fem.mongocleaner.process;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import it.pccube.fem.mongocleaner.bean.LineRecordBean;

@Component
public class Reader {
	
	public LineRecordBean readLineData(String line) {
		LineRecordBean data = new LineRecordBean();
		String[] array = line.split("|");
		data.setEntity(array[0]);
		data.setErrorDescription(array[1]);
		data.setPrimaryKey(array[2]);
		data.setListId(Arrays.asList(array[3].split(";")));
		return data;
	}

}
