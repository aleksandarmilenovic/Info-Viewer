package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.AppWindow;
import helper_classes.Utilities;
import language.Localisation;

public class CloseFile extends AbstractAction {

	
	public CloseFile() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		putValue(AbstractAction.NAME,Localisation.getInstance().getBundle().getString("closefile"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("closefile"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("closеfile_24", ".png"));
	}
	
	public void updateAction(){
		putValue(AbstractAction.NAME,Localisation.getInstance().getBundle().getString("closefile"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("closefile"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AppWindow.getInstance().getjTabbedPane().remove(AppWindow.getInstance().getjTabbedPane().getSelectedComponent());
	//	AppWindow.getInstance().getFileView().repaint();
		
	}
	

}
