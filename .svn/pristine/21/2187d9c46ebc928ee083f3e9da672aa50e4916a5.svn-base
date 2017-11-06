package jsonTABLE;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gui.AppWindow;
import model.db.UIDBFile;

public class DataBaseTable {
	
	private static JSONArray DATA_BASE_TABLES;

	public static void setDataBaseTable(JSONArray wholetables) throws JSONException{
		DATA_BASE_TABLES = wholetables;
	}
	public static void statementExcequte(String nameOfTable, String statementSQL, ArrayList<String> record, ArrayList<String> values, int order) throws SQLException{
		if(order == UIDBFile.INSERT_STATEMENT){
			StatementInstertSQL(statementSQL, record);
		}else if(order == UIDBFile.UPDATE_STATEMENT){
			StatementUpdateSQL(statementSQL, record, values);
		}
		
	}
	public static String getNAMEJSON(String name){
		try {
			for (int i = 0; i < DATA_BASE_TABLES.length(); i++) {
				JSONObject jsonOBJECT = DATA_BASE_TABLES.getJSONObject(i);
				if(jsonOBJECT.has(name)){
					return jsonOBJECT.getString(name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	public static void StatementInstertSQL(String statementSQL, ArrayList<String> record) throws SQLException{
		PreparedStatement preparedStatement = AppWindow.getInstance().getConn().prepareStatement(statementSQL);
		for (int i = 0; i < record.size(); i++) {
			if(record.get(i).trim().equals("")){
				preparedStatement.setObject(i+1, null);
			}else{
				preparedStatement.setObject(i+1, record.get(i));
			}
		}
		preparedStatement.execute();
	}
	
	public static void StatementUpdateSQL(String statementSQL, ArrayList<String> record, ArrayList<String>values) throws SQLException{
		PreparedStatement preparedStatement = AppWindow.getInstance().getConn().prepareStatement(statementSQL);
		for (int i = 0; i < record.size(); i++) {
			if(record.get(i).trim().equals("")){
				preparedStatement.setObject(i+1, null);
			}else{
				preparedStatement.setObject(i+1, record.get(i));
			}
			preparedStatement.setObject(i+record.size() + 1, values.get(i));
		}
		preparedStatement.execute();
	}
	public static boolean jsonContains(String name){
		for (int i = 0; i < DATA_BASE_TABLES.length(); i++) {
			JSONObject jsonOBJECT;
			try {
				jsonOBJECT = DATA_BASE_TABLES.getJSONObject(i);
				if(jsonOBJECT.has(name)){
					return true;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
}
