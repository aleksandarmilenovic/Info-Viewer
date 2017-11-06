package constants;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import gui.AppWindow;
import model.file.UISERFile;


public class ExternalSort extends SwingWorker<Void, Integer> {

	private UISERFile uiSERFile;
	private int[] primaryKeyNum;
	private String[] max;
	private long recordNum;
	private File data;

	public ExternalSort(UISERFile serFile) {
		uiSERFile = serFile;
	}

	private void initData() throws IOException {

		if (uiSERFile.getRECORD_NUM() < 2) {
			JOptionPane.showMessageDialog(null, "Datoteka ne treba da se sortira.",
					"InfoView", 1);
			return;
		}

		File sortedData = new File(uiSERFile.getPath(),
				uiSERFile.getHeaderName().replace(".ser", " SortedFile.txt"));
		if (!sortedData.exists()) {
			sortedData.createNewFile();
		}
		RandomAccessFile aFile = new RandomAccessFile(sortedData, "rw");
		aFile.seek(0);

		String headerSerName = sortedData.getName().replace(".txt", ".ser");
		data = new File(uiSERFile.getPath() + File.separator + headerSerName);
		if (!data.exists()) {
			data.createNewFile();
		}

		RandomAccessFile afile1 = new RandomAccessFile(
				uiSERFile.getPath() + File.separator + uiSERFile.getHeaderName(), "r");
		byte[] temp_buffer = new byte[(int) afile1.length()];

		String tpath = afile1.readLine();
		tpath = "path/" + sortedData.getName();
		afile1.read(temp_buffer);
		afile1.close();

		afile1 = new RandomAccessFile(data.getAbsoluteFile(), "rw");
		afile1.seek(0);
		afile1.writeBytes(tpath + "\r\n");
		afile1.write(temp_buffer);
		afile1.close();

		uiSERFile.setFILE_POINTER(0);
		uiSERFile.setBLOCK_SIZE(1);
		uiSERFile.fetchNextBlock();
		recordNum = uiSERFile.getRECORD_NUM();

		int noPK = 0;
		for (int i = 0; i < uiSERFile.getFields().size(); i++) {
			if (uiSERFile.getFields().get(i).isFieldPK()) {
				noPK++;
			}
		}

		primaryKeyNum = new int[noPK];
		int c = 0;
		
		for (int i = 0; i < uiSERFile.getFields().size(); i++) {
			if (uiSERFile.getFields().get(i).isFieldPK()) {
				primaryKeyNum[c] = i;
				c++;
			}
		}

		
		String[][] serData = uiSERFile.getData();
		
		String[] currentMinRow = serData[0];
		currentMinRow = findFirstMinimum(currentMinRow);
		String row = createString(currentMinRow);
		aFile.writeBytes(row);
		
		max = findFirstMaximum(currentMinRow);
		
		long recordsSorted = 1;
		long duplicates = 0;
		long progress = 0;
		
		while (recordsSorted < 100) {
			duplicates = findAllDuplicates(currentMinRow);
			while (duplicates > 0) {
				aFile.writeBytes(row);
				recordsSorted++;
				duplicates--;
			}

			currentMinRow = findNextMinimum(currentMinRow);
			row = createString(currentMinRow);
			aFile.writeBytes(row);
			recordsSorted++;
			
			progress = 100 * recordsSorted / 100;
			setProgress((int) progress);
			publish(getProgress());
		}
		aFile.writeBytes(createString(max));
		aFile.close();
	}

	private String[] findFirstMinimum(String[] minimum) throws IOException {

		uiSERFile.setFILE_POINTER(0);
		long recordNumber = recordNum;
		String[][] data;
		String[] currentMin = minimum;

		while (recordNumber > 0) {
			uiSERFile.fetchNextBlock();
			data = uiSERFile.getData();
			if (isRowLesser(data[0], currentMin)) {
				currentMin = data[0];
			}
			recordNumber--;
		}
		return currentMin;
	}

	private String[] findFirstMaximum(String[] max) throws IOException {

		uiSERFile.setFILE_POINTER(0);
		long recordNumber = recordNum;
		String[][] data;
		String[] currentMax = max;

		while (recordNumber > 0) {
			uiSERFile.fetchNextBlock();
			data = uiSERFile.getData();
			if (isRowGreater(data[0], currentMax) && !isRowEqualPK(data[0], currentMax)) {
				currentMax = data[0];
			}
			recordNumber--;
		}

		return currentMax;
	}

	private String[] findNextMinimum(String[] minimum) throws IOException {
		
		long recordNumber = recordNum;
		uiSERFile.setFILE_POINTER(0);
		String[][] data;
		String[] currentMin = max;

		while (recordNumber > 0) {
			uiSERFile.fetchNextBlock();
			data = uiSERFile.getData();
			if (isRowLesser(data[0], minimum) || isRowEqualPK(data[0], minimum)) {
				recordNumber--;
				continue;
			} else if (isRowLesser(data[0], currentMin)) {
				currentMin = data[0];
			}
			recordNumber--;
		}
		
		return currentMin;
	}

	private boolean isRowLesser(String[] aData, String[] compareRec) {
		for (int i = 0; i < primaryKeyNum.length; i++) {
			if (!compareRec[primaryKeyNum[i]].trim().equals("")) {
				if (aData[primaryKeyNum[i]].trim().compareToIgnoreCase(compareRec[primaryKeyNum[i]].trim()) > 0) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isRowEqualPK(String[] aData, String[] searchRec) {
		for (int i = 0; i < primaryKeyNum.length; i++) {
			if (!searchRec[primaryKeyNum[i]].trim().equals("")) {
				if (!aData[primaryKeyNum[i]].trim().equals(searchRec[primaryKeyNum[i]].trim())) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isRowGreater(String[] aData, String[] compareRec) {
		for (int i = 0; i < primaryKeyNum.length; i++) {
			if (!compareRec[primaryKeyNum[i]].trim().equals("")) {
				if (aData[primaryKeyNum[i]].trim().compareToIgnoreCase(compareRec[primaryKeyNum[i]].trim()) < 0) {
					return false;
				}
			}
		}
		return true;
	}

	private String createString(String[] row) {
		String result = "";
		for (int i = 0; i < row.length; i++) {
			result += row[i];
		}
		result += "\r\n";
		return result;
	}

	private long findAllDuplicates(String[] row) throws IOException {
		uiSERFile.setFILE_POINTER(0);
		long recordNumber = recordNum;
		String[][] data;
		long countRows = 0;

		while (recordNumber > 0) {
			uiSERFile.fetchNextBlock();
			data = uiSERFile.getData();
			if (isRowEqualPK(data[0], row)) {
				countRows++;
			}
			recordNumber--;
		}
		
		return countRows - 1;
	}

	@Override
	protected Void doInBackground() throws Exception {
		initData();
		return null;
	}

	@Override
	protected void done() {
		uiSERFile.setFILE_POINTER(0);
		uiSERFile.setBLOCK_SIZE(20);
	}
}
