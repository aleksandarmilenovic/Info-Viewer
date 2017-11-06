package actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

import gui.AppWindow;
import helper_classes.Utilities;
import language.Localisation;
import model.ListaModel;
import values.Icons;

public class OpenDriverAction extends AbstractAction {
    

	public OpenDriverAction(String name){
		putValue(AbstractAction.NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		FileSystemView fsv = FileSystemView.getFileSystemView();
	    File f = new File(name); 
		String path = fsv.getSystemTypeDescription(f);
	/*	
	    	if(path.equals("Local Disk")){
		  
	    	putValue(SMALL_ICON,Icons.LOCALDISC_ICON);
	    		
		}else {
			putValue(SMALL_ICON,Icons.DVD_ICON); 	
		}*/
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
         ListaModel listModel = new ListaModel(e.getActionCommand());
         AppWindow.getInstance().getListView().setModel(listModel);
	}

}
