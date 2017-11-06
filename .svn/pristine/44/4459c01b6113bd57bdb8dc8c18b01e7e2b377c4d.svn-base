package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import gui.AppWindow;
import helper_classes.Utilities;

public class AddExtention extends AbstractAction{
 
	public AddExtention() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		putValue(NAME, "Add");
		putValue(SHORT_DESCRIPTION, "Add");
		putValue(SMALL_ICON, Utilities.loadImageIcon("exten_24", ".png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String name = JOptionPane.showInputDialog("Add extention");
		AppWindow.getInstance().getBtoolbar().getBox().addItem(name);
		
	}

}
