package model.db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import gui.AppWindow;
import jsonTABLE.DataBaseTable;

public class DBMetadata {

	public DBMetadata() {

	}

	public void readDatabase() throws SQLException {
		DatabaseMetaData data_base_metadata = AppWindow.getInstance().getConn().getMetaData();

		String[] data_Base_DAta = { "TABLE" };
		ResultSet result_Set_Name = data_base_metadata.getTables(null, null, null, data_Base_DAta);

		String dBTableName;

		DBNode dbNode = (DBNode) AppWindow.getInstance().getDBTree().getModel().getRoot();
		DBNode tableNode;

		while (result_Set_Name.next()) {

			dBTableName = result_Set_Name.getString("TABLE_NAME");

			tableNode = new DBNode(dBTableName, DBNode.DATABASE_TABLE, "", 0, 0, false);

			if (DataBaseTable.jsonContains(dBTableName)) {
				tableNode.setJsonName(DataBaseTable.getNAMEJSON(dBTableName));
			}

			ResultSet primaty_KEYS = data_base_metadata.getPrimaryKeys(null, null, dBTableName);
			ResultSet resultSet_COLUMS = data_base_metadata.getColumns(null, null, dBTableName, null);
			ResultSet secondary_KEYS = data_base_metadata.getImportedKeys(null, null, dBTableName);

			DBNode folderColumns = new DBNode("columns", DBNode.DATABASE_FOLDER, "", 0, 0, false);
			tableNode.add(folderColumns);

			while (resultSet_COLUMS.next()) {

				String columnName = resultSet_COLUMS.getString("COLUMN_NAME");
				String dataType = resultSet_COLUMS.getString("TYPE_NAME");
				boolean isNull = resultSet_COLUMS.getInt("NULLABLE") == 0 ? false : true;

				DBNode columnNode = new DBNode(columnName, DBNode.DATABASE_COLUMN, dataType,
						resultSet_COLUMS.getInt("COLUMN_SIZE"), 0, isNull);
				if (DataBaseTable.jsonContains(columnName)) {
					columnNode.setJsonName(DataBaseTable.getNAMEJSON(columnName));
				}
				while (primaty_KEYS.next()) {
					if (primaty_KEYS.getString("COLUMN_NAME").equals(resultSet_COLUMS.getString("COLUMN_NAME"))) {
						columnNode.setPRIMARY_KEY(true);
						break;
					}
				}

				while (secondary_KEYS.next()) {
					if (secondary_KEYS.getString("FKCOLUMN_NAME").equals(columnName)) {
						columnNode.setFK(true);
						break;
					}
				}

				folderColumns.add(columnNode);
			}

			dbNode.add(tableNode);
		}

		result_Set_Name.close();
		AppWindow.getInstance().getDBTree().expandRow(0);
	}

}
