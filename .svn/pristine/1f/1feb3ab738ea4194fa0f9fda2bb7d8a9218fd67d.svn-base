package model.file;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.event.EventListenerList;

import event.UpdateOverZoneEvent;
import event.UpdateOverZoneListener;
import model.tree.Tree;


public class UIINDFile extends UIAbstractFile {
	
	private Tree tree;
	
	private String treeFileName;
	private String overZoneFileName;
	
	
     protected String[][] overZoneData=null;
	
	
	EventListenerList listenerOverZoneList = new EventListenerList();
	UpdateOverZoneEvent updateOverZoneEvent = null;
	
	public UIINDFile(String path,String headerName, boolean directory, long date) {
		super(path,headerName,directory,date);
	
	}


	public UIINDFile() {
		super();
	}

	public void readHeader() throws IOException{
		   String delimiter = "\\/";
		   ArrayList<String> headRec= new ArrayList<String>();
		   RandomAccessFile headerFile=null;
		   Object data[]=null;
		   
		    headerFile = new RandomAccessFile(path+File.separator+headerName,"r");
			while (headerFile.getFilePointer()<headerFile.length() )
             	headRec.add(headerFile.readLine());
			
			headerFile.close();
			
			   int row = 0;
	         
			   for (String record : headRec) {
			      StringTokenizer tokens = new StringTokenizer(record,delimiter);
			 
			      int cols = tokens.countTokens();
			      data = new String[cols];  
			      int col = 0;
			      while (tokens.hasMoreTokens()) {
			         data[col] = tokens.nextToken();
			         if (data[col].equals("field")){
			        	 String fieldName=tokens.nextToken();
			        	 String fieldType=tokens.nextToken();
			        	 int fieldLenght=Integer.parseInt(tokens.nextToken());
			        	 RECORD_SIZE=RECORD_SIZE+fieldLenght;
			        	 boolean fieldPK=new Boolean(tokens.nextToken());
			        	 UIFileField field=new UIFileField(fieldName,fieldType,fieldLenght,fieldPK);
			        	 
			        	 fields.add(field);
			         }else if (data[col].equals("path")){
			        	    fileName=tokens.nextToken();
			        	
			        	 
			         }else if (data[col].equals("tree")){
			        	 treeFileName=tokens.nextToken();
			         }else if (data[col].equals("overZone")){
			        	 overZoneFileName=tokens.nextToken();
			         }
			         
			         
			      }
            
		          row++;
		
			   }
			   RECORD_SIZE=RECORD_SIZE+2;

			   RandomAccessFile afile=new RandomAccessFile(path+File.separator+fileName,"r");
			   FILE_SIZE=afile.length();
			   RECORD_NUM=(long) Math.ceil((FILE_SIZE*1.0000)/(RECORD_SIZE*1.0000));
			   BLOCK_NUM=(int) (RECORD_NUM/BLOCK_FACTOR)+1;
			   afile.close();
			   //makeTree();
			   openTree(path+File.separator+treeFileName);
			   readOverZone();
			   
	}
	
	public boolean readOverZone() throws IOException{
	       
		  
		  RandomAccessFile afile=new RandomAccessFile(path+File.separator+overZoneFileName,"r");
		  
		  int OVER_ZONE_SIZE=(int) afile.length();
		  buffer=new byte[OVER_ZONE_SIZE];
		 

		  
		  
		  afile.seek(0);
		  afile.read(buffer);
	      String contentS=new String(buffer,"UTF-8");
	      if (contentS.length()<buffer.length){
	    	  for (int x=contentS.length();x<buffer.length;x++);
	    		  contentS=contentS+" ";
	      }
	      
	      overZoneData = new String[(int) OVER_ZONE_SIZE/RECORD_SIZE][];
	      for (int i=0;i<OVER_ZONE_SIZE/RECORD_SIZE;i++){
	    	  
	    	 String line=contentS.substring(i*RECORD_SIZE,i*RECORD_SIZE+RECORD_SIZE);
	    	 overZoneData[i] = new String[fields.size()];

			 int k=0;
			 for (int j=0;j<fields.size();j++){
				String field=null;
			   	field=   line.substring(k,k+fields.get(j).getFieldLength());
			   	overZoneData[i][j]=field;
				k=k+fields.get(j).getFieldLength();
			 }		
	  
	      }
      afile.close();
	    fireUpdateOverZonePerformed();

	return true;
	}	

	public boolean fetchNextBlock() throws IOException{
    	       
				  
			  RandomAccessFile afile=new RandomAccessFile(path+File.separator+fileName,"r");
			  FILE_SIZE=afile.length();
			  RECORD_NUM=(long) Math.ceil((FILE_SIZE*1.0000)/(RECORD_SIZE*1.0000));
			  BLOCK_NUM=(int) (RECORD_NUM/BLOCK_FACTOR)+1;

			  if (FILE_POINTER/RECORD_SIZE+BLOCK_FACTOR>RECORD_NUM) 
				   BUFFER_SIZE=(int) (RECORD_NUM-FILE_POINTER/RECORD_SIZE)*RECORD_SIZE;
			  else 
				   BUFFER_SIZE=(int)(RECORD_SIZE*BLOCK_FACTOR);
			  
			  buffer=new byte[BUFFER_SIZE];
			  data = new String[(int) BUFFER_SIZE/RECORD_SIZE][];

			  afile.seek(FILE_POINTER);
			  afile.read(buffer);
		      String contentS=new String(buffer);
		      if (contentS.length()<buffer.length){
		    	  for (int x=contentS.length();x<buffer.length;x++)
		    		  contentS=contentS+" ";
		      }
		
		      
		      for (int i=0;i<BUFFER_SIZE/RECORD_SIZE;i++){
		    	  
		    	 String line=contentS.substring(i*RECORD_SIZE,i*RECORD_SIZE+RECORD_SIZE);
    			 data[i] = new String[fields.size()]; 
 				 int k=0;
				 for (int j=0;j<fields.size();j++){
					String field=null;
				   	field=   line.substring(k,k+fields.get(j).getFieldLength());
					data[i][j]=field;
					k=k+fields.get(j).getFieldLength();
				 }		
		    	  
		      }
			
			

		    FILE_POINTER=afile.getFilePointer();
			afile.close();

			fireUpdateBlockPerformed();


		return true;
		
     }
	

	
   public boolean addRecord(ArrayList<String> record)throws IOException{
           
	       return true;
   }

   public boolean updateRecord(ArrayList<String> record) throws IOException{
	  
	   return false;
   }

   public boolean findRecord(ArrayList<String> searchRec,int[] position){
	   	   boolean result=false;
	   
	   
	   return result;
   }

	public boolean deleteRecord(ArrayList<String> searchRec) {
		return false;
	}


	public Tree getTree() {
		return tree;
	}


	public void setTree(Tree tree) {
		this.tree = tree;
	}
	
	public void openTree(String treeFilePath){

		ObjectInputStream os=null;
		try {
			os = new ObjectInputStream(new FileInputStream(treeFilePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		  
		try {
			tree = (Tree) os.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	

	@Override
	public boolean findRecord(ArrayList<String> searchRec, int[] position, boolean fromStart) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String[][] findAll(ArrayList<String> searchRec, int[] position) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void sortMDI() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public void addUpdateOverZoneListener(UpdateOverZoneListener l) {
		 listenerOverZoneList.add(UpdateOverZoneListener.class, l);
	 }

	 public void removeUpdateOverZoneListener(UpdateOverZoneListener l) {
		 listenerOverZoneList.remove(UpdateOverZoneListener.class, l);
	 }
	 
	 
	 //kada se izvrsi odgovarajuca akcija, sve observere (slusace) obavestavamo da se dogadjaj desio
	protected void fireUpdateOverZonePerformed() {
	     Object[] listeners = listenerOverZoneList.getListenerList();
	     for (int i = listeners.length-1; i>=0; i-=1) {
	         if (listeners[i]==UpdateOverZoneListener.class) {
	             if (updateOverZoneEvent == null)
	            	 updateOverZoneEvent = new UpdateOverZoneEvent(this);
	             ((UpdateOverZoneListener)listeners[i+1]).updateOverZonePerformed(updateOverZoneEvent);
	         }
	     }

	 }

	

	public String[][] getOverZoneData() {
		return overZoneData;
	}


	public void setOverZoneData(String[][] overZoneData) {
		this.overZoneData = overZoneData;
	}	
	}
