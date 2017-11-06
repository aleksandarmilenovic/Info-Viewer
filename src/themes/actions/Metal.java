package themes.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.AppWindow;
import helper_classes.Utilities;

public class Metal extends AbstractAction {
	
	
	public Metal() {
		putValue(AbstractAction.NAME,"Metal");
		putValue(SHORT_DESCRIPTION, "Metal");
		putValue(SMALL_ICON, Utilities.loadImageIcon("themes_24",".png"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
          SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
	}

}
