package com.geekstyle.gamerecord.model.game;

public class CategoryParam {
	
	public String columnName;
	
	public Object columnValue;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(Object columnValue) {
		this.columnValue = columnValue;
	}

	@Override
	public String toString() {
		return "CategoryParam [columnName=" + columnName + ", columnValue=" + columnValue + "]";
	}
	
}
