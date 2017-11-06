package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.AppWindow;
import helper_classes.Utilities;
import language.Localisation;

public class SaveFile extends AbstractAction{

	
	public SaveFile() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("savefile"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("savefile"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("save_24", ".png"));
	}
	
	public void updateAction(){
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("savefile"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("savefile"));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			AppWindow.getInstance().getFileView().getBtnSave().doClick();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		
	}

}
