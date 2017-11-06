package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.JMException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



import helper_classes.Utilities;
import language.Localisation;
import values.Icons;
import view.DBLogin;
import view.SQLDialog;

public class Menu extends JMenuBar{
	
	private JMenu firstMenu;
	private JMenu secondMenu;
	private JMenu settings;
	private JMenu database;
	private JMenu help;
	private JMenu languages;
	private JMenu themes;
	private JMenu colors;
	private ExtPick epc;
	private JComboBox<String> box;
	
	public Menu() {
		initUI();
	}
	
	private void initUI() {
		initFirstMenu();
		initSecondMenu();
		initDatabase();
		initSetingsMenu();
		initHelpMenu();
		addMenus();
	}
	
	private void initFirstMenu(){
		firstMenu = new JMenu(Localisation.getInstance().getBundle().getString("first"));
		firstMenu.setText(Localisation.getInstance().getBundle().getString("first"));
		//firstMenu.add(AppWindow.getInstance().getActionsManager().getNewFile());
		firstMenu.add(AppWindow.getInstance().getActionsManager().getAllNewFileAction());
		firstMenu.add(AppWindow.getInstance().getActionsManager().getSaveFile());
		firstMenu.add(AppWindow.getInstance().getActionsManager().getEditFileNameAction());
		firstMenu.add(AppWindow.getInstance().getActionsManager().getDelteAction());
		//addExtDialog();
		firstMenu.addSeparator();
		firstMenu.add(AppWindow.getInstance().getActionsManager().getCloseFile());
		firstMenu.add(AppWindow.getInstance().getActionsManager().getCloseAllFiles());

		
	}
	
	private void initSecondMenu() {
		settings = new JMenu(Localisation.getInstance().getBundle().getString("settings"));
		secondMenu = new JMenu(Localisation.getInstance().getBundle().getString("second"));
		secondMenu.setText(Localisation.getInstance().getBundle().getString("second"));
		
	}
	
	private void initSetingsMenu() {
		settings.setText(Localisation.getInstance().getBundle().getString("settings"));
		
		languages = new JMenu(Localisation.getInstance().getBundle().getString("language"));
		languages.setText(Localisation.getInstance().getBundle().getString("language"));
		languages.add(AppWindow.getInstance().getActionsManager().getEnglishAction());
		languages.add(AppWindow.getInstance().getActionsManager().getSerbianCyrillicAction());
		languages.add(AppWindow.getInstance().getActionsManager().getSerbianLatinAction());
		
		themes = new JMenu(Localisation.getInstance().getBundle().getString("themes"));
		themes.add(AppWindow.getInstance().getActionsManager().getWindowsThemeAction());
		themes.add(AppWindow.getInstance().getActionsManager().getWindowsClassicsTheme());
		themes.add(AppWindow.getInstance().getActionsManager().getMotifTheme());
		themes.add(AppWindow.getInstance().getActionsManager().getNimbusTheme());
		
		themes.add(AppWindow.getInstance().getActionsManager().getMetal());
		themes.setText(Localisation.getInstance().getBundle().getString("themes"));
		
		colors = new JMenu(Localisation.getInstance().getBundle().getString("Colors"));
		colors.add(AppWindow.getInstance().getdBButon());
		
		settings.add(languages);
		settings.add(themes);
		settings.add(colors);
	}
	private void initDatabase(){
		database= new JMenu("Database");
		JMenuItem connection = new JMenuItem("Connect");
		connection.setIcon(Icons.CONNECT);
		connection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DBLogin();
			//	AppWindow.getInstance().getActionsManager().getDBLoginAction();
				
			}
		});
		database.add(connection);
	}
	
	private void initHelpMenu() {
		help = new JMenu(Localisation.getInstance().getBundle().getString("help"));
		help.setText(Localisation.getInstance().getBundle().getString("help"));
		help.add(AppWindow.getInstance().getActionsManager().getHelpAction());
		help.add(AppWindow.getInstance().getActionsManager().getClockAction());
		//help.add(AppWindow.getInstance().getActionsManager().getSQLAction());
		JMenuItem sql = new JMenuItem("SQL Help");
		sql.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SQLDialog();
				
			}
		});
		help.add(sql);
		
	}
	
	public void updateMenu(){
		firstMenu.setText(Localisation.getInstance().getBundle().getString("first"));
		settings.setText(Localisation.getInstance().getBundle().getString("settings"));
		languages.setText(Localisation.getInstance().getBundle().getString("language"));
		themes.setText(Localisation.getInstance().getBundle().getString("themes"));
		colors.setText(Localisation.getInstance().getBundle().getString("Colors"));
		help.setText(Localisation.getInstance().getBundle().getString("help"));
	}
	public void addExtDialog(){
		JMenuItem ep=new JMenuItem("Extension Picker");
		firstMenu.add(ep);
		ep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				epc = new ExtPick();
				epc.setVisible(true);
				epc.setLocationRelativeTo(null);
			}
		});
		
	}
	
	private void addMenus() {
		add(firstMenu);
		add(database);
		add(settings);
		add(help);
	}

	public JMenu getFirstMenu() {
		return firstMenu;
	}

	public JMenu getSecondMenu() {
		return secondMenu;
	}

	public JMenu getSettings() {
		return settings;
	}

	public JMenu getHelp() {
		return help;
	}

	public JMenu getLanguages() {
		return languages;
	}

	public JMenu getThemes() {
		return themes;
	}

	public JMenu getColors() {
		return colors;
	}

}
