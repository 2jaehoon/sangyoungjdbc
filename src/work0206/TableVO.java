package work0206;

public class TableVO {
	private String columnName, columnLabel,isNull, defaultValue;
	private int columSize;
	public TableVO() {
	}
	public TableVO(String columnName, String columnLabel, String isNull,  int columSize, String defaultValue) {
		this.columnName = columnName;
		this.columnLabel = columnLabel;
		this.isNull = isNull;
		this.defaultValue = defaultValue;
		this.columSize = columSize;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnLabel() {
		return columnLabel;
	}
	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public int getColumSize() {
		return columSize;
	}
	public void setColumSize(int columSize) {
		this.columSize = columSize;
	}
	@Override
	public String toString() {
		return "TableVO [columnName=" + columnName + ", columnLabel=" + columnLabel + ", isNull=" + isNull
				+ ", defaultValue=" + defaultValue + ", columSize=" + columSize + "]";
	}
	
	
	
	
	
}//class
