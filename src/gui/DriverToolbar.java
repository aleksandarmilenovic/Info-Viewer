package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

import actions.OpenDriverAction;
import helper_classes.Utilities;
import values.Icons;

public class DriverToolbar extends JToolBar{
		private JComboBox<String> box;
		
	public DriverToolbar() {
		
		setOrientation(JToolBar.NORTH);
		setOrientation(JToolBar.HORIZONTAL);
		setFloatable(false);
		
		File[] roots = File.listRoots();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		OpenDriverAction openDriverAction;
		
		for(int index = 0; index < roots.length;index++){
			openDriverAction = new OpenDriverAction(roots[index].toString());
			add(openDriverAction);
			//System.out.println(roots[index].toString());
			//System.out.println(fsv.getSystemTypeDescription(roots[index]));
//			if(fsv.getSystemTypeDescription(roots[index]).equals("Local Disk")){
//				openDriverAction.putValue(AbstractAction.SMALL_ICON, Icons.LOCALDISC_ICON);
//			}else {
//				openDriverAction.putValue(AbstractAction.SMALL_ICON, Icons.DVD_ICON);
//			}
			
			
		//	add(new OpenDriverAction(roots[index].toString()));
		//	addSeparator();
		}
	
		SwingUtilities.updateComponentTreeUI(this);
	}
}
