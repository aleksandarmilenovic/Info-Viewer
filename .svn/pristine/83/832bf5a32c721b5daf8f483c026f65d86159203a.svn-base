package actions.Languages;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import helper_classes.Utilities;
import language.Languages;
import language.Localisation;

public class SerbianCyrillicAction extends AbstractAction {

	public SerbianCyrillicAction() {
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("serbcyrillic"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("serbcyrillic"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("serbian_24", ".png"));
	}

	public void updateAction() {
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("serbcyrillic"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("serbcyrillic"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Localisation.getInstance().languageChange(Languages.SERBIAN_CYRILLIC);

	}

}
