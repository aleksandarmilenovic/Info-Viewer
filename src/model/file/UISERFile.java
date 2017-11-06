package model.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import constants.DataSort;
import gui.AppWindow;

import model.ListaModel;
import view.FileViewInd;

public class UISERFile extends UIAbstractFile {
	private String[][] finalResults;
	private int[] PrimaryKeyes;

	public UISERFile(String path, String headerName, boolean directory, long date) {
		super(path, headerName, directory, date);

	}

	public UISERFile() {
		super();
	}

	public boolean fetchNextBlock() throws IOException {

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + fileName, "r");

		FILE_SIZE = afile.length();

		RECORD_NUM = (long) Math.ceil((FILE_SIZE * 1.0000) / (RECORD_SIZE * 1.0000));

		BLOCK_NUM = (int) (RECORD_NUM / BLOCK_FACTOR) + 1;

		if (FILE_POINTER / RECORD_SIZE + BLOCK_FACTOR > RECORD_NUM)

			BUFFER_SIZE = (int) (RECORD_NUM - FILE_POINTER / RECORD_SIZE) * RECORD_SIZE;
		else
			BUFFER_SIZE = (int) (RECORD_SIZE * BLOCK_FACTOR);

		buffer = new byte[BUFFER_SIZE];
		data = new String[(int) BUFFER_SIZE / RECORD_SIZE][];

		afile.seek(FILE_POINTER);
		afile.read(buffer);

		String contentS = new String(buffer);

		if (contentS.length() < buffer.length) {
			for (int x = contentS.length(); x < buffer.length; x++)
				contentS = contentS + " ";
		}

		for (int i = 0; i < BUFFER_SIZE / RECORD_SIZE; i++) {

			String line = contentS.substring(i * RECORD_SIZE, i * RECORD_SIZE + RECORD_SIZE);

			data[i] = new String[fields.size()];
			int k = 0;
			for (int j = 0; j < fields.size(); j++) {
				String field = null;
				field = line.substring(k, k + fields.get(j).getFieldLength());
				data[i][j] = field;
				k = k + fields.get(j).getFieldLength();
			}
		}

		FILE_POINTER = afile.getFilePointer();
		afile.close();

		fireUpdateBlockPerformed();

		return true;

	}

	public boolean addRecord(ArrayList<String> record) throws IOException {
		try {
			String newRecord = "\r\n";
			for (int i = 0; i < record.size(); i++) {
				newRecord = newRecord + record.get(i);
			}

			RandomAccessFile afile = new RandomAccessFile(path + File.separator + fileName, "rw");

			afile.seek(afile.length());
			afile.writeBytes(newRecord);
			afile.setLength(afile.length());
			afile.close();
		} catch (Exception e) {

		}

		return true;
	}

	public boolean updateRecord(ArrayList<String> record) throws IOException {

		return false;
	}

	private boolean isRowEqual(String[] aData, ArrayList<String> searchRec) {
		boolean result = true;

		for (int col = 0; col < fields.size(); col++) {
			if (!searchRec.get(col).trim().equals("")) {
				if (!aData[col].trim().equals(searchRec.get(col).trim())) {
					result = false;
					return result;
				}
			}

		}

		return result;
	}

	public boolean deleteRecord(ArrayList<String> searchRec) {

		return false;
	}

	@Override
	public void sortMDI() throws IOException {

	}

	@Override
	public void sortMM() throws IOException {

	}

	public UISEKFile makeSEKFile() throws IOException {

		int count = 0;
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).isFieldPK()) {
				count++;
			}
		}
		PrimaryKeyes = new int[count];
		count = 0;
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).isFieldPK()) {
				PrimaryKeyes[count++] = i;
			}
		}
		try {
			sortMDI(fileName);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(AppWindow.getInstance(), e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

			
			return null;
		}

		String headerSekName = headerName.replaceAll(".ser", ".sek");
		File serHeader = new File(path + File.separator + headerSekName);
		if (!serHeader.exists()) {
			serHeader.createNewFile();
		}

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + headerName, "r");
		byte[] temp_buffer = new byte[(int) afile.length()];
		String tpath = afile.readLine();
		tpath = tpath.replace(".txt", ".stxt");
		afile.read(temp_buffer);
		afile.close();

		afile = new RandomAccessFile(serHeader.getAbsoluteFile(), "rw");
		afile.seek(0);
		afile.writeBytes(tpath + "\r\n");
		afile.write(temp_buffer);
		afile.close();

		String fileSekName = fileName.replaceAll(".txt", ".stxt");
		File serText = new File(path + File.separator + fileSekName);
		serText.createNewFile();
		afile = new RandomAccessFile(serText.getAbsoluteFile(), "rw");
		afile.seek(0);

		String[] previousRow = null;
		for (int i = 0; i < data.length; i++) {
			if (!DataSort.isRowEqualPK(data[i], previousRow, PrimaryKeyes)) {
				for (int j = 0; j < fields.size(); j++) {
					afile.writeBytes(data[i][j].toString());
				}
				afile.writeBytes("\r\n");
			}
			previousRow = data[i];
		}

		afile.close();

		AppWindow.getInstance().getListView().setModel(new ListaModel(path));
		UISEKFile uisekFile = new UISEKFile(path, serHeader.getName(), false, 0);
		FileViewInd fileViewSek = new FileViewInd(uisekFile);
		AppWindow.getInstance().setFileViewInd(fileViewSek);
		

		return uisekFile;
	}

	public boolean fileFromSearches(String dataFileName, String serFileName) throws IOException {

		boolean result = true;

		File serHeader = new File(path + File.separator + serFileName);
		if (!serHeader.exists()) {
			try {
				serHeader.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(AppWindow.getInstance(), e.getMessage(), "Fajl vec postoji.",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + headerName, "r");
		byte[] temp_buffer = new byte[(int) afile.length()];

		String tpath = afile.readLine();
		tpath = "path/" + dataFileName;

		afile.read(temp_buffer);
		afile.close();

		afile = new RandomAccessFile(serHeader.getAbsoluteFile(), "rw");
		afile.seek(0);
		afile.writeBytes(tpath + "\r\n");
		afile.write(temp_buffer);
		afile.close();

		File serText = new File(path + File.separator + dataFileName);
		serText.createNewFile();
		afile = new RandomAccessFile(serText.getAbsoluteFile(), "rw");
		afile.seek(0);

		for (int i = 0; i < finalResults.length; i++) {
			for (int j = 0; j < fields.size(); j++) {
				afile.writeBytes(finalResults[i][j].toString());
			}
			afile.writeBytes("\r\n");
		}

		afile.close();

		AppWindow.getInstance().getListView().setModel(new ListaModel(path));
		UISEKFile uisekFile = new UISEKFile(path, serHeader.getName(), false, 0);
		FileViewInd fileViewSek = new FileViewInd(uisekFile);
		AppWindow.getInstance().setFileViewInd(fileViewSek);
		// AppWindow.getInstance().getjTabbedPane().setSelectedIndex(AppWindow.getInstance().getjTabbedPane().getSelectedIndex()
		// + 1);

		return result;
	}

	public String[][] findAll(ArrayList<String> searchRec, int[] position) {

		FILE_POINTER = 0;
		String[][] binSearch = new String[(int) RECORD_NUM][];
		boolean result = false;
		int c = 0;

		while (FILE_POINTER < FILE_SIZE && position[0] == -1) {

			try {
				fetchNextBlock();
			} catch (IOException e) {
				e.printStackTrace();
				position[0] = -1;
				return null;
			}

			for (int row = 0; row < data.length; row++) {

				if (isRowEqual(data[row], searchRec)) {
					binSearch[c++] = data[row];
					result = true;
				}
			}

		}

		if (!result) {
			FILE_POINTER = 0;
			return null;
		}

		if (c > BLOCK_FACTOR) {
			setBLOCK_SIZE(c);
		}

		finalResults = new String[c][];
		for (int i = 0; i < c; i++) {
			finalResults[i] = binSearch[i];
		}

		return finalResults;

	}

	public boolean findRecord(ArrayList<String> searchRec, int[] position, boolean fromStart) {

		RandomAccessFile afile;

		try {
			afile = new RandomAccessFile(path + File.separator + headerName, "r");
			if (FILE_POINTER == afile.length() || fromStart) {
				FILE_POINTER = 0;
				PREVIOUS_FILE_POINTER = -1;
			}
			afile.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		boolean result = false;

		while (FILE_POINTER < FILE_SIZE && position[0] == -1) {
			try {
				fetchNextBlock();
			} catch (IOException e) {
				e.printStackTrace();
				position[0] = -1;
				return false;
			}

			for (int row = 0; row < data.length; row++) {

				if (isRowEqual(data[row], searchRec)) {

					if (PREVIOUS_FILE_POINTER == FILE_POINTER) {
						FILE_POINTER = 0;
						PREVIOUS_FILE_POINTER = -1;
						return false;
					}

					PREVIOUS_FILE_POINTER = FILE_POINTER;
					position[0] = row;
					return true;
				}

			}
		}

		if (!result) {
			FILE_POINTER = 0;
		}

		return result;
	}

}
