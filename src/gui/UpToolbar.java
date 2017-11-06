package gui;

import javax.swing.JToolBar;

public class UpToolbar extends JToolBar{
	
	public UpToolbar() {
		//setOrientation(JToolBar.NORTH);
		//setOrientation(JToolBar.HORIZONTAL);
		setFloatable(false);
		add(AppWindow.getInstance().getActionsManager().getSaveFile());
		add(AppWindow.getInstance().getActionsManager().getCloseFile());
		add(AppWindow.getInstance().getActionsManager().getCloseAllFiles());
	//	add(AppWindow.getInstance().getActionsManager().getHelpAction());
	}

}
