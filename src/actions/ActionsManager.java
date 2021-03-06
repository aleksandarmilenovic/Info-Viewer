package actions;

import javax.swing.JMenuItem;

import actions.Languages.EnglishAction;
import actions.Languages.SerbianCyrillicAction;
import actions.Languages.SerbianLatinAction;
import clock.Actions.ClockAction;
import themes.actions.Metal;
import themes.actions.MotifTheme;
import themes.actions.NimbusTheme;
import themes.actions.WindowsClassicsTheme;
import themes.actions.WindowsTheme;
import view.DBLogin;
import view.SQLDialog;

public class ActionsManager {

	private EnglishAction englishAction;
	private SerbianCyrillicAction serbianCyrillicAction;
	private SerbianLatinAction serbianLatinAction;
	private CloseAllFiles closeAllFiles;
    private CloseFile closeFile;
    private NewFile newFile;
    private SaveFile saveFile;
    private WindowsTheme windowsThemeAction;
    private MotifTheme motifTheme;
    private NimbusTheme nimbusTheme;
    private Metal metal;
    private WindowsClassicsTheme windowsClassicsTheme;
    private HelpAction helpAction;
    private ClockAction clockAction;
	private DelteAction delteAction;
	private EditFileNameAction editFileNameAction;
	private ALLNewFileAction allNewFileAction;
	private AddExtention addExtention;
    private DeleteExt deleteExt;
    private DBLoginAction dblogin;
    private SQLAction sqlAction;
	public ActionsManager() {
		
		englishAction = new EnglishAction();
		serbianCyrillicAction = new SerbianCyrillicAction();
		serbianLatinAction = new SerbianLatinAction();
		
		closeAllFiles = new CloseAllFiles();
		closeFile = new CloseFile();
		newFile = new NewFile();
		saveFile = new SaveFile();
		
		windowsThemeAction = new WindowsTheme();
		motifTheme = new MotifTheme();
		nimbusTheme = new NimbusTheme();
	    metal = new Metal();
	    windowsClassicsTheme = new WindowsClassicsTheme();
	    
	    helpAction = new HelpAction();
	    
	    clockAction = new ClockAction();
        
	    delteAction = new DelteAction();
	    
	    editFileNameAction = new EditFileNameAction();
	    
	    allNewFileAction = new ALLNewFileAction();
	    
	    addExtention = new AddExtention();
	    
	    deleteExt = new DeleteExt();
	}
    public void updateActions(){
    	
    	
    	englishAction.updateAction();
    	serbianCyrillicAction.updateAction();
    	serbianLatinAction.updateAction();
    	
    	closeAllFiles.updateAction();
    	closeFile.updateAction();
    	newFile.updateAction();
    	saveFile.updateAction();
    	
    	helpAction.updateAction();
    	delteAction.updateAction();
    }

	public EnglishAction getEnglishAction() {
		return englishAction;
	}

	public SerbianCyrillicAction getSerbianCyrillicAction() {
		return serbianCyrillicAction;
	}

	public SerbianLatinAction getSerbianLatinAction() {
		return serbianLatinAction;
	}
	public CloseAllFiles getCloseAllFiles() {
		return closeAllFiles;
	}
	public CloseFile getCloseFile() {
		return closeFile;
	}
	public NewFile getNewFile() {
		return newFile;
	}
	public SaveFile getSaveFile() {
		return saveFile;
	}
	public WindowsTheme getWindowsThemeAction() {
		return windowsThemeAction;
	}
	public MotifTheme getMotifTheme() {
		return motifTheme;
	}
	public NimbusTheme getNimbusTheme() {
		return nimbusTheme;
	}
	public Metal getMetal() {
		return metal;
	}
	public WindowsClassicsTheme getWindowsClassicsTheme() {
		return windowsClassicsTheme;
	}
	public HelpAction getHelpAction() {
		return helpAction;
	}
	public ClockAction getClockAction() {
		return clockAction;
	}
	public DelteAction getDelteAction() {
		return delteAction;
	}
	public EditFileNameAction getEditFileNameAction() {
		return editFileNameAction;
	}
	public ALLNewFileAction getAllNewFileAction() {
		return allNewFileAction;
	}
	public AddExtention getAddExtention() {
		return addExtention;
	}
	public DeleteExt getDeleteExt() {
		return deleteExt;
	}
	public DBLoginAction getDBLoginAction() {
		return dblogin;

	}
	public SQLAction getSQLAction() {
		return sqlAction;
	}
	
}
