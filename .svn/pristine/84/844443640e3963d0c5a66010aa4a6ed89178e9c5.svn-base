package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import gui.AppWindow;
import gui.PageRightClickMenu;
import language.Localisation;
import model.UIFile;

public class EditFileNameAction extends AbstractAction{
	
	public EditFileNameAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		putValue(NAME, Localisation.getInstance().getBundle().getString("edit"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("edit"));
		putValue(SMALL_ICON, new ImageIcon(PageRightClickMenu.class.getResource("/images/edit_24.png")));
	}
	
	public void updateAction(){
		putValue(NAME, Localisation.getInstance().getBundle().getString("edit"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("edit"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    try {
	    	Object object = AppWindow.getInstance().getListView().getModel().getElementAt(AppWindow.getInstance().getListView().getSelectedIndex());
			UIFile uiFile = (UIFile) object;
			File file = new File(uiFile.getPath());
			String name =  JOptionPane.showInputDialog(null, file.getName());
			
		//	System.out.println(name);
			File file2 = new File(name);
		//	System.out.println(file.getParent());
			String s = file.getParent()+file2.getName();
			File file3 = new File(s);
		//	System.out.println(s);
			file.renameTo(file3);
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		
		
	}

}
