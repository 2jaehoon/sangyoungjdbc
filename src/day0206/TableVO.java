package day0206;

public class TableVO {
	private String columnName, columnLabel,isNull;
	private int columSize;
	public TableVO() {
	}
	public TableVO(String columnName, String columnLabel, String isNull, int columSize) {
		this.columnName = columnName;
		this.columnLabel = columnLabel;
		this.isNull = isNull;
		this.columSize = columSize;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getcolumnLabel() {
		return columnLabel;
	}
	public void setcolumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
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
				+ ", columSize=" + columSize + "]";
	}
	
	
	
	
}//class
