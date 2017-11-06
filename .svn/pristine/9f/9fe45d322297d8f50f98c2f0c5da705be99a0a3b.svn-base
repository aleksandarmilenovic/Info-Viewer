package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import gui.AppWindow;
import gui.PageRightClickMenu;
import helper_classes.Utilities;
import language.Localisation;
import model.UIFile;
import model.file.UISEKFile;
import values.Icons;

public class ALLNewFileAction extends AbstractAction {
	
	public ALLNewFileAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		putValue(AbstractAction.NAME, Localisation.getInstance().getBundle().getString("newfile"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("newfile"));
	//	putValue(SMALL_ICON, Utilities.loadImageIcon("new_24", ".png"));
		putValue(AbstractAction.SMALL_ICON, new ImageIcon(PageRightClickMenu.class.getResource("/images/new_24.png")));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Object object = AppWindow.getInstance().getListView().getModel().getElementAt(AppWindow.getInstance().getListView().getSelectedIndex());
			UIFile uiFile = (UIFile)object;
			File file = new File(uiFile.getPath());
			String path = file.getParent();
			Object[] possibilities = {"Folder", ".txt", ".ser",".sek",".ind"};
			String s = (String)JOptionPane.showInputDialog(null,"Chose type","New",JOptionPane.PLAIN_MESSAGE,Icons.NEW_FOLDER,possibilities,"Folder");
	        if(s.equals("Folder")){
	        	String name = JOptionPane.showInputDialog("Name");
	        	String sss = path+name;
	        	System.out.println(sss);
	        	if(path.endsWith("\\")){
	        		sss = path+name;
	        	}else {
	        		sss = path+"\\"+name;
	        	}
	        	Path path2 = Paths.get(sss);
	        	try {
					Files.createDirectory(path2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	//Files.createDirectory(path2);
	        }else if(s.equals(".txt")){
	        	String name = JOptionPane.showInputDialog("Name");
	        	String sss = path+name;
	        	if(path.endsWith("\\")){
	        		sss = path+name;
	        	}else {
	        		sss = path+"\\"+name;
	        	}
	        	UIFile uiFile2 = new UIFile(sss, name, false, null, (long) 0);
	        	Path path2 = Paths.get(sss);
	        	PrintWriter writer;
				try {
					writer = new PrintWriter(path2.toString()+".txt", "UTF-8");
				//	writer.println("The first line");
		        //	writer.println("The second line");
		        	writer.close();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }else if(s.equals(".ser")){
	        	String name = JOptionPane.showInputDialog("Name");
	        	String sss = path+name;
	        	if(path.endsWith("\\")){
	        		sss = path+name;
	        	}else {
	        		sss = path+"\\"+name;
	        	}
	        	UIFile uiFile2 = new UIFile(sss, name, false, null, (long) 0);
	        	Path path2 = Paths.get(sss);
	        	PrintWriter writer;
				try {
					writer = new PrintWriter(path2.toString()+".ser", "UTF-8");
				//	writer.println("The first line");
		        //	writer.println("The second line");
		        	writer.close();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        else if(s.equals(".sek")){
	        	String name = JOptionPane.showInputDialog("Name");
	        	String sss = path+name;
	        	if(path.endsWith("\\")){
	        		sss = path+name;
	        	}else {
	        		sss = path+"\\"+name;
	        	}
	        	UISEKFile uiFile2 = new UISEKFile(sss, name, false,  (long) 0);
	        	Path path2 = Paths.get(sss);
	        	PrintWriter writer;
				try {
					writer = new PrintWriter(path2.toString()+".sek", "UTF-8");
				//	writer.println("The first line");
		        //	writer.println("The second line");
		        	writer.close();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        else if(s.equals(".ind")){
	        	String name = JOptionPane.showInputDialog("Name");
	        	String sss = path+name;
	        	if(path.endsWith("\\")){
	        		sss = path+name;
	        	}else {
	        		sss = path+"\\"+name;
	        	}
	        	UISEKFile uiFile2 = new UISEKFile(sss, name, false,  (long) 0);
	        	Path path2 = Paths.get(sss);
	        	PrintWriter writer;
				try {
					writer = new PrintWriter(path2.toString()+".ind", "UTF-8");
				//	writer.println("The first line");
		        //	writer.println("The second line");
		        	writer.close();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "You havent selected any files");
		}
		
		
	}

}
