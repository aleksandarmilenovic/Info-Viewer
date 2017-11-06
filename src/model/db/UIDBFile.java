package model.db;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gui.AppWindow;
import jsonTABLE.DataBaseTable;
import model.FindResult;
import model.file.UIAbstractFile;
import model.file.UIFileField;

public class UIDBFile extends UIAbstractFile {

	protected String TABLE_NAME;
	public static final int INSERT_STATEMENT = 1;
	public static final int UPDATE_STATEMENT = 2;
	public static final int FIND_STATEMENT = 3;
	public static final int SORT_STATEMENT = 4;

	public UIDBFile(String tableName) {
		super("", tableName + ".db", false, (long) 0);
		this.TABLE_NAME = tableName;
	}

	public void readHeader() throws IOException, SQLException {

		DatabaseMetaData dbMetaData = AppWindow.getInstance().getConn().getMetaData();

		ResultSet columnNames = dbMetaData.getColumns(null, null, TABLE_NAME, null);

		ResultSet pkeys = dbMetaData.getPrimaryKeys(null, null, TABLE_NAME);

		while (columnNames.next()) {
			UIFileField field = new UIFileField(columnNames.getString("COLUMN_NAME"),
					"TYPE_" + columnNames.getString("TYPE_NAME").toUpperCase(), columnNames.getInt("COLUMN_SIZE"),
					false);
			fields.add(field);

			while (pkeys.next()) {
				if (pkeys.getString("COLUMN_NAME").equals(field.getFieldName())) {
					field.setFieldPK(true);
					field.setSort(true);
					break;
				}
			}

		}
		pkeys.close();
		columnNames.close();
	}

	public boolean fetchNextBlock() throws IOException, SQLException {

		Statement stmt0 = AppWindow.getInstance().getConn().createStatement();
		ResultSet rs0 = stmt0.executeQuery("SELECT COUNT(*) as broj FROM " + TABLE_NAME);
		if (rs0.next()) {

			RECORD_NUM = rs0.getInt(1);
		}
		stmt0.close();
		rs0.close();

		String columnParams = null;
		for (int i = 0; i < fields.size(); i++) {
			if (columnParams == null) {
				columnParams = fields.get(i).getFieldName();
			} else {
				columnParams += "," + fields.get(i).getFieldName();
			}

		}

		Statement stmt = AppWindow.getInstance().getConn().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT " + columnParams + " FROM " + TABLE_NAME);

		data = new String[(int) RECORD_NUM][];

		int i = 0;
		while (rs.next()) {
			data[i] = new String[fields.size()];
			for (int j = 0; j < fields.size(); j++) {
				data[i][j] = rs.getString(j + 1);
			}
			i++;
		}
		
		fireUpdateBlockPerformed();
		return true;
	}

	public boolean addRecord(ArrayList<String> record) throws IOException, SQLException {
		String insert_SQL_STATEMENT = " INSERT INTO " + TABLE_NAME + " (";

		for (int i = 0; i < fields.size(); i++) {
			insert_SQL_STATEMENT += fields.get(i).getFieldName() + ",";
		}
		insert_SQL_STATEMENT = insert_SQL_STATEMENT.substring(0, insert_SQL_STATEMENT.length() - 1);
		insert_SQL_STATEMENT += ") VALUES (";

		for (int i = 0; i < fields.size(); i++) {
			insert_SQL_STATEMENT += "?,";
		}
		insert_SQL_STATEMENT = insert_SQL_STATEMENT.substring(0, insert_SQL_STATEMENT.length() - 1);
		insert_SQL_STATEMENT += ")";

		DataBaseTable.statementExcequte(TABLE_NAME, insert_SQL_STATEMENT, record, null, INSERT_STATEMENT);

		fetchNextBlock();

		return true;
	}

	public boolean updateRecord(ArrayList<String> record, ArrayList<String> values) throws IOException, SQLException {

		String update_SQL_STATEMET = "UPDATE " + TABLE_NAME + " SET ";

		for (int i = 0; i < fields.size(); i++) {
			update_SQL_STATEMET += fields.get(i).getFieldName() + "=?, ";
		}
		update_SQL_STATEMET = update_SQL_STATEMET.substring(0, update_SQL_STATEMET.length() - 2);
		update_SQL_STATEMET += " WHERE ";

		for (int i = 0; i < fields.size(); i++) {
			update_SQL_STATEMET += fields.get(i).getFieldName() + "=? AND ";
		}
		update_SQL_STATEMET = update_SQL_STATEMET.substring(0, update_SQL_STATEMET.length() - 4);

		try {
			DataBaseTable.statementExcequte(TABLE_NAME, update_SQL_STATEMET, record, values, UPDATE_STATEMENT);
			fetchNextBlock();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(AppWindow.getInstance(), e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteRecord(ArrayList<String> deleteRec) throws IOException, SQLException {

		String deleteSQL = " DELETE FROM " + TABLE_NAME + " WHERE ";

		for (int i = 0; i < fields.size(); i++) {

			if (fields.get(i).isFieldPK()) {
				if (i == 0) {
					deleteSQL += fields.get(i).getFieldName() + "  = ? ";
				} else {
					deleteSQL += " AND " + fields.get(i).getFieldName() + "  =  ?";
				}
			}

		}
		

		PreparedStatement prepareStatement = AppWindow.getInstance().getConn().prepareStatement(deleteSQL);
		for (int i = 0; i < fields.size(); i++) {

			if (fields.get(i).isFieldPK()) {
				prepareStatement.setObject(i + 1, deleteRec.get(i));
			}

		}

		prepareStatement.execute();
		fetchNextBlock();
		return true;

	}

	public boolean find(ArrayList<String> searchRec, FindResult findResult) {

		boolean result = false;
		for (int i = 0; i < RECORD_NUM - 1; i++) {

			result = true;
			for (int j = 0; j < fields.size(); j++) {
				if (!searchRec.get(j).trim().equals("")) {
					if (!data[i][j].trim().equals(searchRec.get(j).trim())) {

						result = false;
						break;
					}
				}
			}

			if (result) {
				findResult.setPosition(i);
				return true;
			}

		}
		return result;
	}

	public boolean filterFind(ArrayList<String> params) throws SQLException {
		String filterfined_SQL_STATEMENT = "SELECT * FROM " + TABLE_NAME + " WHERE ";
		boolean hasParemeters = false;
		for (int i = 0; i < fields.size(); i++) {
			if (params.get(i).length() > 0) {
				if (hasParemeters) {
					filterfined_SQL_STATEMENT += " AND ";
				}
				hasParemeters = true;
				if (fields.get(i).getFieldType().equals(UIFileField.TYPE_CHAR)
						|| fields.get(i).getFieldType().equals(UIFileField.TYPE_VARCHAR)) {
					filterfined_SQL_STATEMENT += fields.get(i).getFieldName() + " LIKE '" + params.get(i) + "'";
				} else {
					filterfined_SQL_STATEMENT += fields.get(i).getFieldName() + params.get(i);
				}
			}
		}

		if (!hasParemeters) {
			filterfined_SQL_STATEMENT = filterfined_SQL_STATEMENT.substring(0, filterfined_SQL_STATEMENT.length() - 7);
		}
		

		Statement statement = AppWindow.getInstance().getConn().createStatement();
		ResultSet resultSet = statement.executeQuery(filterfined_SQL_STATEMENT);

		int number_OF_ROWS;
		for (number_OF_ROWS = 0; resultSet.next(); number_OF_ROWS++)
			;

		resultSet.close();
		resultSet = statement.executeQuery(filterfined_SQL_STATEMENT);
		data = new String[number_OF_ROWS][];

		int i = 0;
		while (resultSet.next()) {
			data[i] = new String[fields.size()];
			for (int j = 0; j < fields.size(); j++) {
				data[i][j] = resultSet.getString(j + 1);
			}
			i++;
		}
		fireUpdateBlockPerformed();
		return true;
	}

	public void sortMDI() throws IOException, SQLException {
		Statement statement = AppWindow.getInstance().getConn().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) as broj FROM " + TABLE_NAME);
		if (resultSet.next()) {

			RECORD_NUM = resultSet.getInt(1);
		}
		statement.close();
		resultSet.close();

		String colum_parametres = null;
		for (int i = 0; i < fields.size(); i++) {
			if (colum_parametres == null) {
				colum_parametres = fields.get(i).getFieldName();
			} else {
				colum_parametres += "," + fields.get(i).getFieldName();
			}

		}

		Statement statement2 = AppWindow.getInstance().getConn().createStatement();
		String order_SQL_STATEMENT = "SELECT " + colum_parametres + " FROM " + TABLE_NAME + " ORDER BY ";
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).isSort()) {
				if (fields.get(i).isAsc()) {
					order_SQL_STATEMENT += fields.get(i).getFieldName() + " ASC, ";
				} else {
					order_SQL_STATEMENT += fields.get(i).getFieldName() + " DESC, ";
				}
			}
		}
		order_SQL_STATEMENT = order_SQL_STATEMENT.substring(0, order_SQL_STATEMENT.length() - 2);
		ResultSet resultSet2 = statement2.executeQuery(order_SQL_STATEMENT);

		data = new String[(int) RECORD_NUM][];

		int i = 0;
		while (resultSet2.next()) {
			data[i] = new String[fields.size()];
			for (int j = 0; j < fields.size(); j++) {
				data[i][j] = resultSet2.getString(j + 1);
			}
			i++;
		}

		fireUpdateBlockPerformed();
	}

	public void sortMM() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean findRecord(ArrayList<String> searchRec, int[] position, boolean fromStart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[][] findAll(ArrayList<String> searchRec, int[] position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRecord(ArrayList<String> record) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
