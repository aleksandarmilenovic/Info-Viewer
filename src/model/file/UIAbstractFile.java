package model.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.StringTokenizer;

import javax.swing.event.EventListenerList;

import event.UpdateBlockEvent;
import event.UpdateBlockListener;

public abstract class UIAbstractFile extends Observable implements IUIFile {
	static public final int BROWSE_MODE = 1;
	static public final int ADD_MODE = 2;
	static public final int UPDATE_MODE = 3;
	static public final int DELETE_MODE = 4;
	static public final int FIND_MODE = 5;

	protected long BLOCK_FACTOR = 20;

	protected int RECORD_SIZE = 0;

	protected int BUFFER_SIZE = 0;

	protected int BLOCK_NUM = 0;

	protected long RECORD_NUM = 0;

	protected long FILE_POINTER = 0;

	protected long FILE_SIZE = 0;

	protected long PREVIOUS_FILE_POINTER = -1;

	protected int MODE = UIAbstractFile.BROWSE_MODE;

	protected String path;

	protected String headerName;

	protected String fileName;

	protected boolean directory;

	public int counter = 0;

	protected ArrayList<UIFileField> fields = new ArrayList<UIFileField>();

	protected byte[] buffer;

	protected String[][] data = null;

	EventListenerList listenerBlockList = new EventListenerList();
	UpdateBlockEvent updateBlockEvent = null;
	private long date;

	public UIAbstractFile(String path, String headerName, boolean directory, Long date) {
		this.path = path;
		this.headerName = headerName;
		this.directory = directory;
		this.fileName = headerName;
		this.date = date;

	}

	public UIAbstractFile() {
	}

	public void readHeader() throws IOException, SQLException {
		String delimiter = "\\/";
		ArrayList<String> headRec = new ArrayList<String>();
		RandomAccessFile headerFile = null;
		Object data[] = null;

		headerFile = new RandomAccessFile(path + File.separator + headerName, "r");
		while (headerFile.getFilePointer() < headerFile.length())
			headRec.add(headerFile.readLine());

		headerFile.close();

		int row = 0;

		for (String record : headRec) {
			StringTokenizer tokens = new StringTokenizer(record, delimiter);

			int cols = tokens.countTokens();
			data = new String[cols];
			int col = 0;
			while (tokens.hasMoreTokens()) {
				data[col] = tokens.nextToken();
				if (data[col].equals("field")) {
					String fieldName = tokens.nextToken();
					String fieldType = tokens.nextToken();
					int fieldLenght = Integer.parseInt(tokens.nextToken());

					RECORD_SIZE = RECORD_SIZE + fieldLenght;
					boolean fieldPK = new Boolean(tokens.nextToken());
					UIFileField field = new UIFileField(fieldName, fieldType, fieldLenght, fieldPK);

					fields.add(field);
				} else if (data[col].equals("path")) {
					fileName = tokens.nextToken();

				}

			}

			row++;

		}

		RECORD_SIZE = RECORD_SIZE + 2;

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + fileName, "r");
		FILE_SIZE = afile.length();
		RECORD_NUM = (long) Math.ceil((FILE_SIZE * 1.0000) / (RECORD_SIZE * 1.0000));
		BLOCK_NUM = (int) (RECORD_NUM / BLOCK_FACTOR) + 1;
		afile.close();
	}

	public void addUpdateBlockListener(UpdateBlockListener l) {
		listenerBlockList.add(UpdateBlockListener.class, l);
	}

	public void removeUpdateBlockListener(UpdateBlockListener l) {
		listenerBlockList.remove(UpdateBlockListener.class, l);
	}

	protected void fireUpdateBlockPerformed() {
		Object[] listeners = listenerBlockList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == UpdateBlockListener.class) {
				if (updateBlockEvent == null)
					updateBlockEvent = new UpdateBlockEvent(this);
				((UpdateBlockListener) listeners[i + 1]).updateBlockPerformed(updateBlockEvent);
			}
		}

	}

	public void setBLOCK_SIZE(long block_size) {
		BLOCK_FACTOR = block_size;
		BLOCK_NUM = (int) (RECORD_NUM / BLOCK_FACTOR) + 1;
	}

	public void sortMDI(String sortFileName) throws IOException {

		byte[] sort_buffer = new byte[(int) (RECORD_SIZE * (RECORD_NUM + 1))];
		String[][] sort_data = new String[(int) RECORD_NUM][];

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + sortFileName, "r");

		afile.seek(0);
		afile.read(sort_buffer);
		afile.close();
		String contentS = new String(sort_buffer);
		if (contentS.length() < sort_buffer.length) {
			for (int x = contentS.length(); x < sort_buffer.length; x++)
				contentS = contentS + " ";
		}

		for (int i = 0; i < RECORD_NUM; i++) {

			String line = contentS.substring(i * RECORD_SIZE, i * RECORD_SIZE + RECORD_SIZE);

			sort_data[i] = new String[fields.size()];
			int k = 0;
			for (int j = 0; j < fields.size(); j++) {
				String field = null;
				field = line.substring(k, k + fields.get(j).getFieldLength());

				sort_data[i][j] = field;
				k = k + fields.get(j).getFieldLength();
			}

		}

		
		String minValue = "";
		String[] temp;
		for (int f = 0; f < fields.size(); f++) {
			if (fields.get(f).isSort()) {
				for (int i = 0; i < RECORD_NUM - 1; i++) {
					minValue = sort_data[i][f];
					int k = i;

					for (int j = i + 1; j < RECORD_NUM; j++) {

						boolean comp = true;
						for (int p = 0; p < f; p++) {
							if (fields.get(p).isSort()) {
								if (sort_data[i][p].compareToIgnoreCase(sort_data[j][p]) != 0) {
									comp = false;
								}
							}
						}
						
						if (((minValue.compareToIgnoreCase(sort_data[j][f]) > 0 && fields.get(f).isAsc())
								|| (minValue.compareToIgnoreCase(sort_data[j][f]) < 0 && !fields.get(f).isAsc()))
								&& comp) {
							minValue = sort_data[j][f];
							k = j;
						}
					}

					temp = sort_data[i];
					sort_data[i] = sort_data[k];
					sort_data[k] = temp;
				} 
			} 
		}
			

		data = sort_data;
		for (int i = 0; i < sort_data.length - 1; i++) {
			for (int j = 0; j < sort_data.length; j++) {

			}
		}

		fireUpdateBlockPerformed();
	}

	public void sortMM() throws IOException {

		byte[] sort_buffer = new byte[(int) (RECORD_SIZE * RECORD_NUM)];

		int F = (int) Math.ceil(Math.log(RECORD_NUM) / Math.log(2));
	
		int fd = (int) (Math.pow(2, F) - RECORD_NUM);

		String[][] sort_data1 = new String[(int) RECORD_NUM + fd][];

		String[][] sort_data2 = new String[(int) RECORD_NUM + fd][];

		String[][] temp = new String[(int) RECORD_NUM + fd][];

		RandomAccessFile afile = new RandomAccessFile(path + File.separator + fileName, "r");
		afile.seek(0);
		afile.read(sort_buffer);
		afile.close();
		
		String contentS = new String(sort_buffer);
		if (contentS.length() < sort_buffer.length) {
			for (int x = contentS.length(); x < sort_buffer.length; x++)
				contentS = contentS + " ";
		}

		for (int i = 0; i < RECORD_NUM; i++) {

			String line = contentS.substring(i * RECORD_SIZE, i * RECORD_SIZE + RECORD_SIZE);

			sort_data1[i] = new String[fields.size()];
			int k = 0;
			for (int j = 0; j < fields.size(); j++) {
				String field = null;
				field = line.substring(k, k + fields.get(j).getFieldLength());
				sort_data1[i][j] = field;
				k = k + fields.get(j).getFieldLength();
			}

		}
		
		addTempRecords(sort_data1, fd);

		
		for (int f = 0; f < fields.size(); f++) {

			if (fields.get(f).isSort()) {
				int d = 1;
			
				int k = (int) Math.floor((RECORD_NUM + fd) / 2);

				while (k >= 1) {
					
					int j = 1;
					while (j <= k) {
						sortGrupe(sort_data1, sort_data2, d, j, f);
						j++;
					}
					
					k = k / 2;
					
					d = 2 * d;
					temp = sort_data1;
					sort_data1 = sort_data2;
					sort_data2 = temp;
				}
			}
		}

		
		data = removeTempRecords(sort_data1, fd);
		fireUpdateBlockPerformed();

	}

	private void sortGrupe(String[][] source, String[][] dest, int d, int j, int f) {
		
		int t = 2 * (j - 1) * d + 1; 
		int p = 2 * (j - 1) * d + 1; 
		int q = (2 * j - 1) * d + 1; 
		while (t <= 2 * j * d) {
			if (p > (2 * j - 1) * d) {
				dest[t - 1] = source[q - 1];
				q++;

			} else {
				if (q > 2 * j * d) {
					dest[t - 1] = source[p - 1];
					p++;
				} else {
					boolean comp = true;
					for (int f2 = 0; f2 < f; f2++) {
						if (fields.get(f2).isSort()) {
							if (source[p - 1][f2].compareToIgnoreCase(source[q - 1][f2]) != 0) {
								comp = false;
							}
						}
					}

					if ((

					(source[p - 1][f].compareToIgnoreCase(source[q - 1][f]) < 0 && fields.get(f).isAsc())
							|| (source[p - 1][f].compareToIgnoreCase(source[q - 1][f]) > 0 && !fields.get(f).isAsc()))
							&& comp) {

						dest[t - 1] = source[p - 1];
						p++;
					} else {
						dest[t - 1] = source[q - 1];
						q++;
					}

				}
			}

			t++;
		}

	}

	private void addTempRecords(String[][] sort_data1, int fd) {
		for (int i = (int) RECORD_NUM; i < RECORD_NUM + fd; i++) {
			sort_data1[i] = new String[RECORD_SIZE];
			for (int j = 0; j < fields.size(); j++) {
				String field = null;
				for (int x = 0; x < fields.get(j).getFieldLength(); x++) {
					field = "~";
				}
				sort_data1[i][j] = field;
			}
		}

	}

	private String[][] removeTempRecords(String[][] sort_data1, int fd) {
		String[][] temp = new String[(int) RECORD_NUM][];
		int k = 0;
		for (int i = 0; i < RECORD_NUM + fd; i++) {

			if (!sort_data1[i][0].contains("~")) {
				temp[k] = new String[RECORD_SIZE];
				temp[k] = sort_data1[i];
				k++;
			}
		}
		return temp;

	}

	protected void makeSortPK() {
		for (int i = 0; i < fields.size(); i++) {
			fields.get(i).setSort(fields.get(i).isFieldPK());
			fields.get(i).setAsc(fields.get(i).isFieldPK());
		}

	}

	public static int getADD_MODE() {
		return ADD_MODE;
	}

	public static int getBROWSE_MODE() {
		return BROWSE_MODE;
	}

	public static int getDELETE_MODE() {
		return DELETE_MODE;
	}

	public static int getFIND_MODE() {
		return FIND_MODE;
	}

	public static int getUPDATE_MODE() {
		return UPDATE_MODE;
	}

	public int getBLOCK_NUM() {
		return BLOCK_NUM;
	}

	public long getBLOCK_FACTOR() {
		return BLOCK_FACTOR;
	}

	public byte[] getBlockContent() {
		return buffer;
	}

	public String[][] getData() {
		return data;
	}

	public boolean isDirectory() {
		return directory;
	}

	public ArrayList<UIFileField> getFields() {
		return fields;
	}

	public long getFILE_POINTER() {
		return FILE_POINTER;
	}

	public long getFILE_SIZE() {
		return FILE_SIZE;
	}

	public String getFileName() {
		return fileName;
	}

	public String getHeaderName() {
		return headerName;
	}

	public int getMODE() {
		return MODE;
	}

	public String getPath() {
		return path;
	}

	public long getRECORD_NUM() {
		return RECORD_NUM;
	}

	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}

	public void setMODE(int mode) {
		MODE = mode;
	}

	public String toString() {
		return fileName;
	}

	public String getDate() {
		File folder = new File(path);
		Path pathh = folder.toPath();
		BasicFileAttributes attr = null;
		try {
			attr = Files.readAttributes(pathh, BasicFileAttributes.class);

		} catch (IOException e) {
			System.out.println("greska" + e.getMessage());
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String rez1 = sdf1.format(attr.creationTime().toMillis());
		return rez1;
	}

	public String getLastModifiedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String rez = sdf.format(date);
		return rez;
	}

	public void setFILE_POINTER(int newFilePointer) {
		FILE_POINTER = newFilePointer;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public void notifyObservers(Object arg0) {
		setChanged();
		super.notifyObservers(arg0);
	}
	
}
