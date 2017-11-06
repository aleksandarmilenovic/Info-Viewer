package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import gui.AppWindow;
import helper_classes.Utilities;
import language.Localisation;
import values.Icons;

public class CloseAllFiles extends AbstractAction{

	public CloseAllFiles() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("closeallfiles"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("closeallfiles"));
		putValue(SMALL_ICON, Icons.CLOSE_ALL);
	}
	
	public void updateAction(){
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("closeallfiles"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("closeallfiles"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Napraviti Akciju koja zatvara sve otvorene fajlove
		AppWindow.getInstance().getjTabbedPane().removeAll();
		SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
		
	}

}
