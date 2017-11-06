package model.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import constants.DataSort;
import gui.AppWindow;

import model.ListaModel;
import model.tree.KeyElement;
import model.tree.Node;
import model.tree.NodeElement;
import model.tree.Tree;
import view.FileViewInd;
import view.MergeWindow;;

public class UISEKFile extends UIAbstractFile {

	private File changeDataFile;
	private String changeDataName;
	private String[][] changeddata;
	private UISEKFile newChangedSEK;
	private UISERFile newChangedSER;
	private UISEKFile errorSEK;

	public UISEKFile(String path, String headerName, boolean directory, long date) {
		super(path, headerName, directory, date);

	}

	public void initChangeData() {
		if (changeDataFile != null) {
			return;
		}
		changeDataName = fileName.replaceAll(".sek", "DatotekaIzmena.txt");
		changeDataFile = new File(path + File.separator + changeDataName);
		try {
			changeDataFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void makeINDFile() throws IOException {
		makeTree();
		String headerINDName = makeINDHeader();

		UIINDFile uifile = new UIINDFile(path, headerINDName, false, 0);
		uifile.FILE_POINTER = 0;
		FileViewInd fileView = new FileViewInd(uifile);
		AppWindow.getInstance().setFileViewInd(fileView);

	}

	public String makeINDHeader() throws IOException {

		String headerINDName = headerName.replaceAll(".sek", ".ind");
		File indHeader = new File(path + File.separator + headerINDName);
		if (!indHeader.exists()) {
			indHeader.createNewFile();
		}

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + headerName, "r");
		byte[] temp_buffer = new byte[(int) afile.length()];
		afile.read(temp_buffer);
		afile.close();

		afile = new RandomAccessFile(indHeader.getAbsoluteFile(), "rw");
		afile.seek(0);
		afile.writeBytes("tree/" + headerName.replaceAll(".sek", ".tree") + "\r\n");
		afile.writeBytes("overZone/" + headerName.replaceAll(".sek", ".over") + "\r\n");
		afile.write(temp_buffer);
		afile.setLength(afile.length());
		afile.close();
		return headerINDName;
	}

	public void makeTree() throws IOException {
		FILE_POINTER = 0;
		List<Node> listNodes = new ArrayList<Node>();

		Tree tree = null;
		long tFilePointer = 0;

		while (FILE_POINTER < FILE_SIZE) {
			tFilePointer = FILE_POINTER;
			fetchNextBlock();
			List<KeyElement> listKeyElements = new ArrayList<KeyElement>();

			List<NodeElement> listNodeElements = new ArrayList<NodeElement>();
			for (int i = 0; i < fields.size(); i++) {
				if (fields.get(i).isFieldPK()) {
					KeyElement keyElement = new KeyElement(fields.get(i).getFieldType(), data[0][i]);
					listKeyElements.add(keyElement);
				}

			}

			NodeElement nodeElement = new NodeElement((int) (tFilePointer / RECORD_SIZE), listKeyElements);

			listNodeElements.add(nodeElement);
			Node node = new Node(listNodeElements);
			tFilePointer = FILE_POINTER;
			fetchNextBlock();
			listKeyElements = new ArrayList<KeyElement>();

			for (int i = 0; i < fields.size(); i++) {
				if (fields.get(i).isFieldPK()) {
					KeyElement keyElement = new KeyElement(fields.get(i).getFieldType(), data[0][i]);
					listKeyElements.add(keyElement);
				}

			}

			nodeElement = new NodeElement((int) (tFilePointer / RECORD_SIZE), listKeyElements);

			listNodeElements.add(nodeElement);
			node = new Node(listNodeElements);
			listNodes.add(node);
		}

		Node root = new Node();

		tree = new Tree();
		tree.setRootElement(root);

		FILE_POINTER = 0;

		ObjectOutputStream os;
		String treeFileName = headerName.replaceAll(".sek", ".tree");
		try {
			os = new ObjectOutputStream(new FileOutputStream(path + File.separator + treeFileName));
			os.writeObject(tree);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public UISEKFile() {
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

		boolean result = true;
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).isFieldPK()) {
				if (record.get(i).trim().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Niste uneli vrednost primarnog kljuca " + fields.get(i).getFieldName(), "Info View", 1);
					return false;
				} else {

					temp.add(record.get(i));
				}
			} else {
				temp.add(" ");
			}

		}
		int[] position = new int[1];
		position[0] = -1;
		if (findRecord(temp, position, true)) {
			JOptionPane.showMessageDialog(null, "Slog sa istom vrednošću već postoji", "UI Project", 1);
			return false;

		}
		long oldFilePointer = (FILE_POINTER / RECORD_SIZE - BLOCK_FACTOR) * RECORD_SIZE;
		long newPosition = FILE_POINTER / RECORD_SIZE - BLOCK_FACTOR + position[0];

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + fileName, "rw");
		byte[] record_buffer = new byte[RECORD_SIZE];
		for (int i = (int) RECORD_NUM - 1; i >= newPosition; i--) {
			afile.seek(i * RECORD_SIZE);
			afile.read(record_buffer);
			afile.write(record_buffer);
		}
		String newRecord = "";
		for (int i = 0; i < record.size(); i++) {
			newRecord = newRecord + record.get(i);
		}

		newRecord = newRecord + "\r\n";
		afile.seek(newPosition * RECORD_SIZE);
		afile.writeBytes(newRecord);
		afile.close();
		FILE_POINTER = oldFilePointer;
		fetchNextBlock();
		return result;

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

	private boolean isRowGreater(String[] aData, ArrayList<String> searchRec) {
		boolean result = true;
		int noPK = 0;
		boolean prev = true;

		for (int i = 0; i < fields.size(); i++) {
			if (!searchRec.get(i).trim().equals("") && !fields.get(i).isFieldPK()) {

				return false;
			}
			if (fields.get(i).isFieldPK())
				noPK++;

		}

		for (int col = 0; col < fields.size(); col++) {

			if (!searchRec.get(col).trim().equals("")) {

				if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) > 0 && col < noPK - 1) {
					return true;

				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) != 0 && col < noPK - 1) {
					result = false;
					prev = false;
				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) <= 0) {
					result = false;

				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) > 0 && prev
						&& col == (noPK - 1)) {
					result = true;
				}
			}
		}

		return result;
	}

	public boolean deleteRecord(ArrayList<String> searchRec) {
		String newRecord = "D";
		for (int i = 0; i < searchRec.size(); i++) {
			newRecord = newRecord + searchRec.get(i);
		}
		newRecord += "\r\n";
		RandomAccessFile changeFile;
		try {
			changeFile = new RandomAccessFile(changeDataFile, "rw");
			changeFile.seek(changeFile.length());
			changeFile.writeBytes(newRecord);
			changeFile.setLength(changeFile.length());
			changeFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean binaryAlgoritm(ArrayList<String> searchRec) {

		long left = 1;
		long right = RECORD_NUM;
		long tmpSlog = 0;

		setBLOCK_SIZE(1);

		while (left <= right) {

			tmpSlog = (left + right) / 2;
			FILE_POINTER = tmpSlog * RECORD_SIZE;

			try {
				fetchNextBlock();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

			if (isRowEqual(data[0], searchRec)) {
				return true;
			}

			if (isRowGreaterBinary(data[0], searchRec)) {
				right = tmpSlog - 1;
			} else {
				left = tmpSlog + 1;
			}
		}

		return false;

	}

	private boolean isRowGreaterBinary(String[] aData, ArrayList<String> searchRec) {
		boolean result = true;
		int noPK = 0;
		boolean prev = true;

		for (int i = 0; i < fields.size(); i++) {
			if (!searchRec.get(i).trim().equals("") && !fields.get(i).isFieldPK()) {

				continue;
			}
			if (fields.get(i).isFieldPK())
				noPK++;

		}

		for (int col = 0; col < fields.size(); col++) {

			if (!searchRec.get(col).trim().equals("") && fields.get(col).isFieldPK()) {

				if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) > 0 && col < noPK - 1) {
					return true;

				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) != 0 && col < noPK - 1) {
					result = false;
					prev = false;
				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) <= 0) {
					result = false;

				} else if (aData[col].trim().compareToIgnoreCase(searchRec.get(col).trim()) > 0 && prev
						&& col == (noPK - 1)) {
					result = true;
				}
			}
		}

		return result;
	}

	@Override
	public boolean findRecord(ArrayList<String> searchRec, int[] position, boolean fromStart) {
		RandomAccessFile afile;
		try {
			afile = new RandomAccessFile(path + File.separator + fileName, "r");
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
				} else if (isRowGreater(data[row], searchRec)) {
					position[0] = row;
					return false;
				}

			}
		}

		if (!result) {
			FILE_POINTER = 0;
		}

		return result;
	}

	@Override
	public String[][] findAll(ArrayList<String> searchRec, int[] position) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isRowEqualPK(String[] aData, ArrayList<String> searchRec) {

		boolean result = true;

		for (int col = 0; col < fields.size(); col++) {

			if (fields.get(col).isFieldPK()) {
				if (!aData[col].trim().equals(searchRec.get(col).trim())) {
					return false;
				}
			}

		}
		return result;
	}

	public boolean isRowEqualPKChange(String[] aData, String[] searchRec) {
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).isFieldPK()) {
				if (!aData[i + 1].trim().equals(searchRec[i + 1].trim())) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean updateSEKFile() throws IOException {
		changeSerFile();

		updateSerToSek();

		return true;
	}

	private boolean changeSerFile() throws IOException {

		boolean result = true;

		String headerSerName = headerName.replaceAll(".sek", "DatotekaPromena.ser");
		File serHeader = new File(path + File.separator + headerSerName);
		System.out.println(serHeader + "//// " + serHeader.getAbsolutePath());
		if (!serHeader.exists()) {
			serHeader.createNewFile();
		}

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + headerName, "r");
		byte[] temp_buffer = new byte[(int) afile.length()];

		String tpath = afile.readLine();
		afile.read(temp_buffer);
		afile.close();

		String changeField = "field/CHANGE/TYPE_VARCHAR/1/false\r\n";
		System.out.println(changeDataName);
		afile = new RandomAccessFile(serHeader.getAbsoluteFile(), "rw");
		afile.seek(0);
		afile.writeBytes("path/" + changeDataName + "\r\n");
		afile.writeBytes(changeField);
		afile.write(temp_buffer);
		afile.close();

		// serijska datoteka je kreirana
		newChangedSER = new UISERFile(path, serHeader.getName(), false, 0);
		FileViewInd fileViewInd = new FileViewInd(newChangedSER);
		// AppWindow.getInstance().setFileViewInd(fileViewInd);

		return result;
	}

	private void updateSerToSek() throws IOException {

		String resultSekName = headerName.replaceAll(".sek", "PoslePromena.stxt");
		File resultSekFile = new File(path + File.separator + resultSekName);
		if (!resultSekFile.exists()) {
			resultSekFile.createNewFile();
		}
		RandomAccessFile sekDataFile = new RandomAccessFile(resultSekFile, "rw");
		sekDataFile.seek(0);

		String headerSekName = headerName.replaceAll(".sek", "PoslePromena.sek");
		File sekHeader = new File(path + File.separator + headerSekName);
		if (!sekHeader.exists()) {
			sekHeader.createNewFile();
		}

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + headerName, "r");
		byte[] temp_buffer = new byte[(int) afile.length()];
		String tpath = afile.readLine();
		tpath = "path/" + resultSekName;
		afile.read(temp_buffer);
		afile.close();

		afile = new RandomAccessFile(sekHeader.getAbsoluteFile(), "rw");
		afile.seek(0);
		afile.writeBytes(tpath + "\r\n");
		afile.write(temp_buffer);
		afile.close();

		String errors = headerName.replaceAll(".sek", " Errors.txt");
		File errorFile = new File(path + File.separator + errors);
		if (!errorFile.exists()) {
			errorFile.createNewFile();
		}
		RandomAccessFile errorWriter = new RandomAccessFile(errorFile, "rw");
		errorWriter.seek(0);

		String[][] changesData;
		String[] currentRow;
		String[] nextRow = null;
		String command;
		ArrayList<String> record = new ArrayList<>();
		int changeLogs = (int) newChangedSER.getRECORD_NUM() - 1;
		int serRow = 0;

		newChangedSER.FILE_POINTER = 0;
		newChangedSER.sortMDI(newChangedSER.fileName);
		changesData = newChangedSER.getData();
		currentRow = changesData[serRow];
		if (changeLogs >= 1) {
			serRow++;
			nextRow = changesData[serRow];
			while (isRowEqualPKChange(currentRow, nextRow)) {
				String errorString = "***Not executed, only the last command on the same row is executed(based on row PK)!!\r\n ================================================================================================\r\n";
				errorString += DataSort.createStringFromRow(currentRow);
				errorString += "================================================================================================\r\n\r\n";
				currentRow = nextRow;
				errorWriter.writeBytes(errorString);
				if (serRow < changeLogs) {
					serRow++;
					nextRow = changesData[serRow];
				} else {
					break;
				}
			}
		}
		command = currentRow[0];

		for (int j = 0; j < fields.size(); j++) {
			record.add(currentRow[j + 1]);
		}

		setBLOCK_SIZE(1);
		FILE_POINTER = 0;

		while (FILE_POINTER < FILE_SIZE) {

			fetchNextBlock();

			if (isRowEqualPK(data[0], record)) {

				if (command.equals("D")) {

				} else if (command.equals("M")) {

					String newRecord = "";
					for (int i1 = 0; i1 < record.size(); i1++) {
						newRecord = newRecord + record.get(i1);
					}
					newRecord = newRecord + "\r\n";
					sekDataFile.writeBytes(newRecord);
					command = "S";
				} else if (command.equals("I")) {
					String newRecord = "***Can't create row with this key, already exists! \r\n ================================================================================================\r\n";
					for (int i1 = 0; i1 < record.size(); i1++) {
						newRecord = newRecord + " " + record.get(i1);
					}
					newRecord = newRecord
							+ "\r\n================================================================================================\r\n\r\n";
					errorWriter.writeBytes(newRecord);
					command = "S";

					String slog = "";
					for (int k = 0; k < data.length; k++) {
						for (int e = 0; e < fields.size(); e++) {
							slog += data[k][e];
						}
					}
					slog += "\r\n";
					sekDataFile.writeBytes(slog);
				}

				if (changeLogs - serRow >= 0 && nextRow != null && !nextRow.equals(currentRow)) {

					record.clear();
					currentRow = nextRow;

					if (serRow < changeLogs) {
						serRow++;
						nextRow = changesData[serRow];
						while (isRowEqualPKChange(currentRow, nextRow)) {
							String errorString = "***Not executed, only the last command on the same row is executed(based on row PK)!!\r\n ================================================================================================\r\n";
							errorString += DataSort.createStringFromRow(currentRow);
							errorString += "\r\n================================================================================================\r\n\r\n";
							currentRow = nextRow;
							errorWriter.writeBytes(errorString);
							if (serRow < changeLogs) {
								serRow++;
								nextRow = changesData[serRow];
							} else {
								break;
							}
						}
					}

					command = currentRow[0];

					for (int j = 0; j < fields.size(); j++) {
						record.add(currentRow[j + 1]);
					}

				} else {
					command = "S";
				}

			} else if (isRowGreaterBinary(data[0], record) && !command.equals("S")) {
				if (command.equals("I")) {
					// System.out.println("Row greater i I upisuje u novu.");
					String newRecord = "";
					for (int i1 = 0; i1 < record.size(); i1++) {
						newRecord = newRecord + record.get(i1);
					}
					newRecord = newRecord + "\r\n";
					sekDataFile.writeBytes(newRecord);
					command = "S";
				} else if (command.equals("M")) {
					String newRecord = "***Can't modify, row doesn't exist! \r\n ================================================================================================\r\n";
					for (int i1 = 0; i1 < record.size(); i1++) {
						newRecord = newRecord + " " + record.get(i1);
					}
					newRecord = newRecord
							+ "\r\n ================================================================================================\r\n\r\n";
					errorWriter.writeBytes(newRecord);
					command = "S";
				}

				if (changeLogs - serRow >= 0 && nextRow != null && !nextRow.equals(currentRow)) {

					record.clear();
					currentRow = nextRow;

					if (serRow < changeLogs) {
						serRow++;
						nextRow = changesData[serRow];
						while (isRowEqualPKChange(currentRow, nextRow)) {
							String errorString = "***Not executed, only the last command on the same row is executed(based on row PK)!!\r\n ================================================================================================\r\n";
							errorString += DataSort.createStringFromRow(currentRow);
							errorString += "\r\n ================================================================================================\r\n\r\n";
							errorWriter.writeBytes(errorString);
							currentRow = nextRow;
							if (serRow < changeLogs) {
								serRow++;
								nextRow = changesData[serRow];
							} else {
								break;
							}
						}
					}

					command = currentRow[0];
					for (int j = 0; j < fields.size(); j++) {
						record.add(currentRow[j + 1]);
					}

				} else {
					command = "S";
				}

			} else {

				String slog = "";
				for (int k = 0; k < data.length; k++) {
					for (int e = 0; e < fields.size(); e++) {
						slog += data[k][e];
					}
				}
				slog += "\r\n";
				sekDataFile.writeBytes(slog);
			}

		}
		sekDataFile.close();

		setBLOCK_SIZE(20);

		if (errorWriter.length() > 0) {
			errorWriter.close();
		} else {
			errorWriter.close();
			errorFile.delete();
		}

		MergeWindow mergeDialog = new MergeWindow(this);
		mergeDialog.setVisible(true);
		UISEKFile uifile = new UISEKFile(path, sekHeader.getName(), false, 0);
		FileViewInd fileViewind = new FileViewInd(uifile);
		// AppWindow.getInstance().setFileViewInd(fileViewind);
	}

	public void deleteChangeData() throws IOException {
		newChangedSER.notifyObservers("delete");
		changeDataFile.delete();
		File deleteSER = new File(path + File.separator + newChangedSER.getHeaderName());
		deleteSER.delete();
	}

	@Override
	public void sortMDI() throws IOException {
		// TODO Auto-generated method stub

	}

	public boolean editRecord(ArrayList<String> record) throws IOException {

		String newRecord = "M";
		for (int i = 0; i < record.size(); i++) {
			newRecord = newRecord + record.get(i);
		}
		newRecord += "\r\n";
		RandomAccessFile changeFile = new RandomAccessFile(changeDataFile, "rw");
		changeFile.seek(changeFile.length());
		changeFile.writeBytes(newRecord);
		changeFile.setLength(changeFile.length());
		changeFile.close();
		return true;

	}

	public boolean addByOrder(ArrayList<String> record) throws IOException {

		String newRecord = "I";
		for (int i = 0; i < record.size(); i++) {
			newRecord = newRecord + record.get(i);
		}
		newRecord += "\r\n";
		RandomAccessFile changeFile = new RandomAccessFile(changeDataFile, "rw");
		changeFile.seek(changeFile.length());
		changeFile.writeBytes(newRecord);
		changeFile.setLength(changeFile.length());
		changeFile.close();
		return true;
	}
}
