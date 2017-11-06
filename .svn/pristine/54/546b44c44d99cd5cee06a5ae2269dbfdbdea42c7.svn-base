package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import gui.AppWindow;
import model.file.UIFileField;

@SuppressWarnings("serial")
public class TabelaModel extends DefaultTableModel{

	public boolean isCellEditable(int row, int column) {
		return false;
	}
	public TabelaModel( ArrayList<UIFileField> fields,Object[][] data) {
		
		   super(data,fields.toArray());
		   try{
			   setDataVector(data,fields.toArray());
			}catch(OutOfMemoryError e){
				JOptionPane.showMessageDialog(AppWindow.getInstance(),e.getMessage(),"Greška",JOptionPane.ERROR_MESSAGE);
			}
	}

}
