package language;

import java.util.Locale;
import java.util.ResourceBundle;

import gui.AppWindow;


public class Localisation {
	
	private static Localisation instance = null;
	private ResourceBundle bundle;
	private Languages language;
	
	public static Localisation getInstance() {
		if(instance == null){
			instance = new Localisation();
		}
		return instance;
	}
	
	public Localisation() {
		Locale.setDefault(new Locale("en", "UK"));
		bundle = ResourceBundle.getBundle("language.ApplicationResources", Locale.getDefault());
		language = Languages.ENGLISH;
	}

	public void updateComponents(){
		AppWindow.getInstance().updateComponent();
     	AppWindow.getInstance().getActionsManager().updateActions();
     	AppWindow.getInstance().getMenu().updateMenu();
	}
	public void languageChange(Languages language) {

		switch (language) {
		case ENGLISH:
			Locale.setDefault(new Locale("en", "UK"));
			break;
		case SERBIAN_CYRILLIC:
			Locale.setDefault(new Locale("sr", "RS", "Cyrillic"));
			break;
		case SERBIAN_LATIN:
			Locale.setDefault(new Locale("sr", "RS", "Latin"));
			break;
		default:
			break;
		}
		this.language = language;
		bundle = ResourceBundle.getBundle("language.ApplicationResources", Locale.getDefault());
		updateComponents();
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public Languages getLanguage() {
		return language;
	}
	
}
