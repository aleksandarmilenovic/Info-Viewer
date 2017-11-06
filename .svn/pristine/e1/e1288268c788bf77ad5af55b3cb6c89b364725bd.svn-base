package model.db;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class DBNode extends DefaultMutableTreeNode {

	public final static int DATABASE = 0;
	public final static int DATABASE_TABLE = 1;
	public final static int DATABASE_COLUMN = 2;
	public final static int PRIMARY_KEY = 3;
	public final static int DATABASE_FOLDER = 4;

	private String name;
	private int type;
	private String dataType;
	private int columnSize;
	private int decimalDigits;
	private boolean isNull;
	private boolean isPRIMARY_KEY;
	private String jNAME = null;
	private boolean isSECONDARY_KEY;

	public DBNode(String name, int type, String dataType, int columnSize, int decimalDigits, boolean isNull) {
		super();
		this.columnSize = columnSize;
		this.dataType = dataType;
		this.decimalDigits = decimalDigits;
		this.isNull = isNull;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public boolean isPRIMARY_KEY() {
		return isPRIMARY_KEY;
	}

	public void setPRIMARY_KEY(boolean isPRIMARY_KEY) {
		this.isPRIMARY_KEY = isPRIMARY_KEY;
	}

	public boolean isSECONDARY_KEY() {
		return isSECONDARY_KEY;
	}

	public void setFK(boolean isSECONDARY_KEY) {
		this.isSECONDARY_KEY = isSECONDARY_KEY;
	}

	public void setJsonName(String jNAME) {
		this.jNAME = jNAME;
	}

	public String getJsonName() {
		return jNAME;
	}

	public String toString() {
		if (type == DBNode.DATABASE_COLUMN) {
			if (jNAME != null) {
				return jNAME + " (" + dataType + " (" + columnSize + "), " + ((isNull) ? "NULL" : "NOT NULL") + " ) ";
			}
			return name + " (" + dataType + " (" + columnSize + "), " + ((isNull) ? "NULL" : "NOT NULL") + " ) ";
		} else if (type == DBNode.DATABASE_TABLE) {
			if (jNAME == null) {
				return name;
			} else {
				return jNAME;
			}
		}
		return name;

	}
}
