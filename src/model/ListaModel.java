package model;

import java.io.File;

import javax.swing.DefaultListModel;

import gui.AppWindow;
import model.file.UIINDFile;
import model.file.UISEKFile;
import model.file.UISERFile;

public class ListaModel extends DefaultListModel{
	
	public ListaModel() {
		super();
	}
	
	public ListaModel(String path){
	   
		try {
	    	
		File folder = new File(path);
		File[] allFolders = folder.listFiles();
		
		String namepath = folder.getParent();	
		UIFile fakeRoot = new UIFile(namepath,"...",true,"",(long) 0);
		addElement(fakeRoot);
		
		String name = AppWindow.getInstance().getBtoolbar().getBox().getSelectedItem().toString();
		if(name.equals("ALL")){
			for(int i=0; i < allFolders.length; i++){
				if(allFolders[i].getName().endsWith(".ind")){
					UIINDFile uifile=new UIINDFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
			  	    addElement(uifile);
				}
				if(allFolders[i].getName().endsWith(".sek")){
				UISEKFile uifile=new UISEKFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
		  	    addElement(uifile);
		  	    }else if(allFolders[i].getName().endsWith(".ser")){
					 UISERFile uifile=new UISERFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
		  	            addElement(uifile);
				}else if(allFolders[i].getName().endsWith(".txt") || allFolders[i].isDirectory()){
				UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
				addElement(uiFile);			
				}
			}
		}else if(name.equals(".txt")){
			for(int i=0; i < allFolders.length; i++){
				if(allFolders[i].getName().contains(".txt")){
					UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
					addElement(uiFile);
				}
			}
		}else if(name.equals(".ser")){
			for(int i=0; i < allFolders.length; i++){
				
				if(allFolders[i].getName().contains(".ser")){
					 UISERFile uifile=new UISERFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
		  	            addElement(uifile);
				}
			}
		}else if(name.equals(".sek")){
			for(int i=0; i < allFolders.length; i++){
				
				if(allFolders[i].getName().contains(".sek")){
					 UISEKFile uifile=new UISEKFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
		  	            addElement(uifile);
				}
			}
		}else if(name.equals(".ind")){
			for(int i=0; i < allFolders.length; i++){
				
				if(allFolders[i].getName().contains(".ind")){
					 UIINDFile uifile=new UIINDFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
		  	            addElement(uifile);
				}
			}
		}else if(name.equals("Diectory")){
			for(int i=0; i < allFolders.length; i++){
				
				if(allFolders[i].isDirectory() ){
				UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
				addElement(uiFile);			
				}
			}
		}else if(name.equals("Directory and .txt")){
			for(int i=0; i < allFolders.length; i++){
				if(allFolders[i].isDirectory() || allFolders[i].getName().contains(".txt")){
				UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
				addElement(uiFile);			
				}
			}
		}else if(name.equals("Directory and .ser")){
			for(int i=0; i < allFolders.length; i++){
				if(allFolders[i].isDirectory()){
					UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
					addElement(uiFile);	
				}
				if(allFolders[i].getName().contains(".ser")){
				 UISERFile uifile=new UISERFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
		  	       addElement(uifile);	
				}
			}
			}else if(name.equals("Directory and .sek")){
				for(int i=0; i < allFolders.length; i++){
					if(allFolders[i].isDirectory()){
						UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
						addElement(uiFile);	
					}
					if(allFolders[i].getName().contains(".sek")){
					 UISEKFile uifile=new UISEKFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
			  	       addElement(uifile);	
					}
				}
				}
			else if(name.equals("Directory and .ind")){
				for(int i=0; i < allFolders.length; i++){
					if(allFolders[i].isDirectory()){
						UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
						addElement(uiFile);	
					}
					if(allFolders[i].getName().contains(".ind")){
					 UIINDFile uifile=new UIINDFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
			  	       addElement(uifile);	
					}
				}
				}
			else{
			for(int i=0; i < allFolders.length; i++){
				if(name.equals(".ser")){
					UISERFile uifile=new UISERFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
			  	       addElement(uifile);
				}else if(name.equals(".sek")){
					UISEKFile uifile=new UISEKFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
			  	       addElement(uifile);
				}else if(name.equals(".ind")){
					UIINDFile uifile=new UIINDFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory(),(long)allFolders[i].lastModified());
			  	       addElement(uifile);
				}else if(name.equals(".txt") || allFolders[i].isDirectory()){
					UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
					addElement(uiFile);
				}		
			}
		}
/*		for(int i=0; i < allFolders.length; i++){
			
			if(allFolders[i].getName().contains(".ser")){
				 UISERFile uifile=new UISERFile(allFolders[i].isDirectory()?allFolders[i].getAbsolutePath(): allFolders[i].getParentFile().getAbsolutePath(),allFolders[i].getName(),allFolders[i].isDirectory());
	  	            addElement(uifile);
			}else{
			UIFile uiFile = new UIFile(allFolders[i].getAbsolutePath(), allFolders[i].getName(),allFolders[i].isDirectory(), "",(long)allFolders[i].lastModified());
			addElement(uiFile);			
			}
		}
	*/	
		
		} catch (Exception e) {
		//	JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE); 
		}
		
		
	}

}
