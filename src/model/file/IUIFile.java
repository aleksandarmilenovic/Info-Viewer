package model.file;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;



public interface IUIFile {
	
	
	public boolean findRecord(ArrayList<String> searchRec,int[] position, boolean fromStart);
	public void readHeader() throws IOException, SQLException;
	public boolean fetchNextBlock() throws IOException, SQLException;
	public boolean addRecord(ArrayList<String> record) throws IOException, SQLException;
	public boolean updateRecord(ArrayList<String> record)throws IOException;
	public String[][] findAll(ArrayList<String> searchRec,int[] position);
	public boolean deleteRecord(ArrayList<String> searchRec) throws IOException, SQLException;
	public void sortMDI()throws IOException, SQLException;
	public void sortMM()throws IOException;
}
