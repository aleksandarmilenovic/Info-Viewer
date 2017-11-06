package actions;



import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.AppWindow;
import helper_classes.Utilities;
import language.Localisation;
import view.DBLogin;

public class DBLoginAction extends AbstractUIAction {


	
	public DBLoginAction() {
	//	putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("aboutaction"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("aboutaction"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("info_24", ".png"));

		/*putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/?.png"));
		putValue(NAME, "Connect Database");
		putValue(SHORT_DESCRIPTION, "Connect Database");*/
	}

	public void actionPerformed(ActionEvent arg0) {
			new DBLogin();
			//AppWindow.getInstance().dbLogin();
	}

}
