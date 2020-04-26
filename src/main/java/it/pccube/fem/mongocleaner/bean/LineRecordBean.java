package it.pccube.fem.mongocleaner.bean;

import java.util.List;

public class LineRecordBean {
	
	private String entity;
	
	private String primaryKey;
	
	private String errorDescription;
	
	private List<String> listId;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public List<String> getListId() {
		return listId;
	}

	public void setListId(List<String> listId) {
		this.listId = listId;
	}

	@Override
	public String toString() {
		return "LineRecordBean [entity=" + entity + ", primaryKey=" + primaryKey + ", errorDescription="
				+ errorDescription + ", listId=" + listId + "]";
	}
	
	
	

}
