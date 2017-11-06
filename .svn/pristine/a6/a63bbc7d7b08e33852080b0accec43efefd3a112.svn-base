package themes.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.AppWindow;
import helper_classes.Utilities;
import language.Localisation;

public class WindowsTheme extends AbstractAction{

	public WindowsTheme() {
		putValue(AbstractAction.NAME,"Windows");
		putValue(SHORT_DESCRIPTION, "Windows");
		putValue(SMALL_ICON, Utilities.loadImageIcon("themes_24",".png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
	}

}
