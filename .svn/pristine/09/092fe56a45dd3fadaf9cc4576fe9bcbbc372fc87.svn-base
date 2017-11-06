package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import gui.AppWindow;
import gui.PageRightClickMenu;
import language.Localisation;
import model.UIFile;
import model.file.UIINDFile;
import model.file.UISEKFile;
import model.file.UISERFile;

public class DelteAction extends AbstractAction {
	
	public DelteAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
		putValue(NAME, Localisation.getInstance().getBundle().getString("delte"));
		putValue(SHORT_DESCRIPTION,Localisation.getInstance().getBundle().getString("delte"));
		putValue(SMALL_ICON, new ImageIcon(PageRightClickMenu.class.getResource("/images/trash_24.png")));
	}

	public void updateAction(){
		putValue(NAME, Localisation.getInstance().getBundle().getString("delte"));
		putValue(SHORT_DESCRIPTION,Localisation.getInstance().getBundle().getString("delte"));
	}
	
	public static boolean deleteDir(File file) {
	    if (file.isDirectory()) {
	        String[] children = file.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(file, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return file.delete(); 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	//	int size = file.listFiles().length;
		try {
		Object object = AppWindow.getInstance().getListView().getModel().getElementAt(AppWindow.getInstance().getListView().getSelectedIndex());
		if(object instanceof UISERFile){
			  UISERFile uiserFile =(UISERFile)object;
			  String s ;
			  if(uiserFile.getPath().endsWith("\\")){
				  s = uiserFile.getPath()+uiserFile.getFileName();
			  }else{
				  s = uiserFile.getPath()+"\\"+uiserFile.getFileName();
			  }
			  File file = new File(s);
			  file.delete();
			  AppWindow.getInstance().getListView().deleteFile();
			}else if(object instanceof UISEKFile){
				  UISEKFile uiserFile =(UISEKFile)object;
				  String s ;
				  if(uiserFile.getPath().endsWith("\\")){
					  s = uiserFile.getPath()+uiserFile.getFileName();
				  }else{
					  s = uiserFile.getPath()+"\\"+uiserFile.getFileName();
				  }
				  File file = new File(s);
				  file.delete();
				  AppWindow.getInstance().getListView().deleteFile();
				}else if(object instanceof UIINDFile){
					  UIINDFile uiserFile =(UIINDFile)object;
					  String s ;
					  if(uiserFile.getPath().endsWith("\\")){
						  s = uiserFile.getPath()+uiserFile.getFileName();
					  }else{
						  s = uiserFile.getPath()+"\\"+uiserFile.getFileName();
					  }
					  File file = new File(s);
					  file.delete();
					  AppWindow.getInstance().getListView().deleteFile();
					}
		
		int i = AppWindow.getInstance().getListView().getSelectedIndex();
		UIFile uiFile = (UIFile) object;
		File file = new File(uiFile.getPath());
			if(uiFile.isDirectory()){
				int size = file.listFiles().length;
			   if(size == 0){
				   file.delete();
			   }else {
				   int n = JOptionPane.showConfirmDialog(null, "Folder is not empty.Do you want to delte it?", "Delte", JOptionPane.YES_NO_OPTION);
				   
			        if(n == JOptionPane.YES_OPTION){
			        	deleteDir(file);
			        	AppWindow.getInstance().getListView().deleteFile();
			            JOptionPane.showMessageDialog(null, "Your folders have been deleted");
			            SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
			        }
			        else {
			            JOptionPane.showMessageDialog(null, "You didnt delte any folders.");
			        } 
			   }
			//   AppWindow.getInstance().getListView().remove(AppWindow.getInstance().getListView().getSelectedIndex());
			}else {
				file.delete();
				AppWindow.getInstance().getListView().deleteFile();
			//	SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
			}
		    //AppWindow.getInstance().getListView().deleteFile();
			//AppWindow.getInstance().getListView().remove(i);
		} catch (Exception e) {
			//e.printStackTrace();
		//	JOptionPane.showMessageDialog(null, "You didnt select any folders or files.");
			JOptionPane.showMessageDialog(null, "You havent selected any files");
		}
		

	}

}
