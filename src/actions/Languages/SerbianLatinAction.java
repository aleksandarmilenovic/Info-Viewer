package actions.Languages;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import helper_classes.Utilities;
import language.Languages;
import language.Localisation;

public class SerbianLatinAction extends AbstractAction {

	public SerbianLatinAction() {
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("serblatin"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("serblatin"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("serbian_24", ".png"));
	}

	public void updateAction() {
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("serblatin"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("serblatin"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Localisation.getInstance().languageChange(Languages.SERBIAN_LATIN);

	}

}
