package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.AppWindow;
import values.Icons;

public class DeleteExt extends AbstractAction {
	
	
	public DeleteExt() {
		putValue(NAME, "Remove");
		putValue(SHORT_DESCRIPTION, "Remove extention");
		putValue(SMALL_ICON, Icons.SKULL);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AppWindow.getInstance().getBtoolbar().getBox().removeItem(AppWindow.getInstance().getBtoolbar().getBox().getSelectedItem());;

	}

}
