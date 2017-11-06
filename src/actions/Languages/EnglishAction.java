package actions.Languages;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import helper_classes.Utilities;
import language.Languages;
import language.Localisation;

public class EnglishAction extends AbstractAction {

	public EnglishAction() {
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("eng"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("eng"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("english_24", ".png"));
	}

	public void updateAction() {
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("eng"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("eng"));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Localisation.getInstance().languageChange(Languages.ENGLISH);

	}

}
