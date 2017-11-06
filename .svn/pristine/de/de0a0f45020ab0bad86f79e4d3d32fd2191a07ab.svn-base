package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class PageRightClickMenu extends JPopupMenu {
	
	public PageRightClickMenu() {
			
			JMenuItem add=new JMenuItem("New");
			add.setIcon(new ImageIcon(PageRightClickMenu.class.getResource("/images/new_24.png")));
			JMenuItem edit=new JMenuItem("Edit");
			edit.setIcon(new ImageIcon(PageRightClickMenu.class.getResource("/images/edit_24.png")));
			AbstractAction abstractAction = AppWindow.getInstance().getActionsManager().getNewFile();
		//	abstractAction.putValue(AbstractAction.SMALL_ICON, new ImageIcon(PageRightClickMenu.class.getResource("/images/newfolder_24.png")));
			//JMenuItem delete=AppWindow.getInstance().getActionsManager().getDelteAction();
		//	delete.setIcon(new ImageIcon(PageRightClickMenu.class.getResource("/images/trash_24.png")));
			
			
			
			
			//add(abstractAction);
			add(AppWindow.getInstance().getActionsManager().getAllNewFileAction());

			add(AppWindow.getInstance().getActionsManager().getEditFileNameAction());

			add(AppWindow.getInstance().getActionsManager().getDelteAction());

			add(new JSeparator());
			add(AppWindow.getInstance().getActionsManager().getAddExtention());

			
		}
}
