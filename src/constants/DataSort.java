package constants;

public class DataSort {
	
	public static String createStringFromRow(String[] row) {
		String result = "";
		for (int i = 0; i < row.length; i++) {
			result += row[i];
		}
		result += "\r\n";
		return result;
	}

	
	public boolean isRowLesserPK(String[] aData, String[] compareRec, int[]mPKList) {
		for (int i = 0; i < mPKList.length; i++) {
			if (!compareRec[mPKList[i]].trim().equals("")) {
				if (aData[mPKList[i]].trim().compareToIgnoreCase(compareRec[mPKList[i]].trim()) > 0) {
					return false;
				}
			}
		}
		return true;
	}

	

	public static boolean isRowGreaterPK(String[] aData, String[] compareRec, int[]mPKList) {
		for (int i = 0; i < mPKList.length; i++) {
			if (!compareRec[mPKList[i]].trim().equals("")) {
				if (aData[mPKList[i]].trim().compareToIgnoreCase(compareRec[mPKList[i]].trim()) < 0) {
					return false;
				}
			}
		}
		return true;
	}
	public static boolean isRowEqualPK(String[] aData, String[] searchRec, int[]mPKList) {
		if(aData == null || searchRec == null){
			return false;
		}
		for (int i = 0; i < mPKList.length; i++) {
			if (!searchRec[mPKList[i]].trim().equals("")) {
				if (!aData[mPKList[i]].trim().equals(searchRec[mPKList[i]].trim())) {
					return false;
				}
			}
		}
		return true;
	}
	
}
