package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import gui.AppWindow;
import helper_classes.Utilities;
import language.Localisation;

public class NewFile extends AbstractAction{

	public NewFile() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("newfile"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("newfile"));
		putValue(SMALL_ICON, Utilities.loadImageIcon("new_24", ".png"));
	}
	
	public void updateAction(){
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("newfile"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("newfile"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("\\Desktop"));
		chooser.setDialogTitle("New file");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(chooser.showOpenDialog(AppWindow.getInstance()) == JFileChooser.APPROVE_OPTION){
			
		}
	//	System.out.println(chooser.getSelectedFile().getAbsolutePath());
	//	File f = new File(chooser.getSelectedFile().getAbsolutePath());
		
		try {
			Path path = Paths.get(chooser.getSelectedFile().getAbsolutePath());
			Files.createDirectory(path);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
		//	JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
		}
	}

}
