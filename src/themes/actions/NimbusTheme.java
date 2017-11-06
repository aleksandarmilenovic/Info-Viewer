package themes.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.AppWindow;
import helper_classes.Utilities;

public class NimbusTheme extends AbstractAction {
	
	public NimbusTheme() {
		putValue(AbstractAction.NAME,"Nimbus");
		putValue(SHORT_DESCRIPTION, "Nimbus");
		putValue(SMALL_ICON, Utilities.loadImageIcon("themes_24",".png"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());

	}

}
