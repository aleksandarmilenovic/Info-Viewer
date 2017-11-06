package actions.Languages;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Directory {
	
	static String xStrPath;
	static double[][] myArray;
	
	static void CreateDir(){
		
		boolean checkDir;
	
		//char xdeleteDir = 'n';
		Path xPath = Paths.get(xStrPath);
		checkDir = Files.exists(xPath);
		
		if(checkDir){
			System.out.println("Directory Alredy Exists");
			return;
		}
		
		try{
			Files.createDirectories(xPath);
		}
		catch(Exception e){
			System.out.println("Could not create directory");
		}	
	}
	static void DeleteDir(){
		Path xDeletePath= Paths.get(xStrPath);
		try {
			Files.delete(xDeletePath);
		} catch (Exception e) {
			System.out.println("Could not delete directory");
		}
	}	
}
