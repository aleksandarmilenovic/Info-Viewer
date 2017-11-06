package actions;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import helper_classes.Utilities;
import language.Localisation;

public class HelpAction extends AbstractAction{
	
	public HelpAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		putValue(NAME, Localisation.getInstance().getBundle().getString("help"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("help"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("quest_24", ".png"));

	}
	
	public void updateAction(){
		putValue(NAME, Localisation.getInstance().getBundle().getString("help"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("help"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0){
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI("https://student.ftn.uns.ac.rs/redmine/projects/tim-uinf-2015-201-4/wiki"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
