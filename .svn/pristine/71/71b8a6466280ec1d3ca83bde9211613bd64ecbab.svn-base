package model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

public class UIFile {
	
	private String path;
	private String fileName;
	private boolean directory;
	private String content;
	private long date;
	
	public UIFile(String path, String fileName, boolean directory, String content, Long date) {
		super();
		this.path = path;
		this.fileName = fileName;
		this.directory = directory;
		this.content = content;
		this.date=date;
	} 
	
	public UIFile(){
		super();
	}
	
	public void readFile(){
		try {
			
			RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
			content = "";
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()){
				content = content+"\n"+randomAccessFile.readLine();
			}
			randomAccessFile.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveFile(String content){
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
			randomAccessFile.writeBytes(content);
			randomAccessFile.setLength(content.length());
			randomAccessFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDate(){
		  File folder = new File(path);
		  Path pathh = folder .toPath();
		     BasicFileAttributes attr = null;
		     try {
		     attr = Files.readAttributes(pathh, BasicFileAttributes.class);
		     
		     } catch (IOException e) {
		     System.out.println("greska" + e.getMessage());
		     }
		  SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  String rez1 = sdf1.format(attr.creationTime().toMillis());
		     return rez1;
		 }
		 
		 public String getLastModifiedDate(){
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  String rez = sdf.format(date); 
		  return rez;
		 }
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString(){
		return fileName;
	}

}
