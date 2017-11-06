package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.AboutDialog;
import helper_classes.Utilities;
import language.Localisation;


public class AboutAction extends AbstractAction{

	public AboutAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("aboutaction"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("aboutaction"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("info_24", ".png"));
	}
	
	public void updateAction(){
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("aboutaction"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("aboutaction"));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 new AboutDialog();
		
	}

}
