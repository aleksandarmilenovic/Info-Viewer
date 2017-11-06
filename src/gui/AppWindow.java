package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import actions.ActionsManager;
import helper_classes.Utilities;
import language.Localisation;
import model.db.DBMetadata;
import model.db.DBNode;
import model.db.DBTree;
import view.DBLogin;
import view.FileView;
import view.FileViewDATABASE;
import view.FileViewInd;
import view.ListaCellRenderer;
import view.ListaView;
import view.SQLDialog;

public class AppWindow extends JFrame{
 
	private static AppWindow instance = null;
	private ActionsManager actionsManager;
	private Menu menu;
	private UpToolbar upToolbar;
	private DriverToolbar toolbar;
	private BottomToolbar btoolbar;
    private JPanel jPanel;
    private JPanel jPanel2;
    private JPanel north;
	private ListaView listView;
	private FileView fileView;
	private JPanel frameWork;
	private PageRightClickMenu rightclick;
	private DBLogin dblogin;

	
	private JTabbedPane jTabbedPane;
	private JPanel datab;
	private JPanel panLeft;
	
	private JMenuItem dBButon = new JMenuItem("Set Color");
	
	private FileViewInd fileViewInd;
	private FileViewDATABASE fileViewDB;
	
	private Connection conn=null;
    private DBTree dbTree=null;
	public AppWindow (){
	}
	
	public static AppWindow getInstance(){
		if(instance == null){
			instance = new AppWindow();
		//  instance.initList();
			
			instance.inittabpane();
			instance.init();
			
		//	instance.initGUI();
		} return instance;
	}
	
	private void init(){
	actionsManager = new ActionsManager();
	initialiseDBTree();
	initList();
	initGUI();
	}
	
	private void initGUI(){
		jPanel = new JPanel();
		
		menu = new Menu();
		upToolbar = new UpToolbar();
		toolbar = new DriverToolbar();
		setTitle(Localisation.getInstance().getBundle().getString("appname"));
		setIconImage(Utilities.loadBufferedImage("developer_icon_24", ".png"));
		setSize(1100, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(menu);
		
		getContentPane().add(toolbar, BorderLayout.NORTH);
		jPanel = new JPanel();
		jPanel2 = new JPanel();
		north = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel2.setLayout(new BorderLayout());
		jPanel.setPreferredSize(new Dimension(50, 60));
		jPanel2.setPreferredSize(new Dimension(50, 60));
		jPanel.add(upToolbar,BorderLayout.NORTH);
		jPanel2.add(toolbar,BorderLayout.NORTH);
		north.setLayout(new GridLayout(2, 1));
		north.setPreferredSize(new Dimension(60, 75));
		north.add(jPanel);
		north.add(jPanel2);
		
		getContentPane().add(north, BorderLayout.NORTH);
		
		panLeft=new JPanel();	
   	    panLeft.setPreferredSize(new Dimension(100,225));
   	    panLeft.setBorder(BorderFactory.createTitledBorder("File Chooser"));
   	    
   	    
   	    rightclick=new PageRightClickMenu();
 
   	    panLeft.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me){
				if(me.getButton() == MouseEvent.BUTTON3){
					rightclick.show(me.getComponent(),me.getX(),me.getY());
				}
			}
		});
		
   	    frameWork=new JPanel(new BorderLayout());
		frameWork.setBackground(Color.LIGHT_GRAY);
		
		datab=new JPanel(new BorderLayout());
		datab.setBorder(BorderFactory.createTitledBorder("Database"));

		getContentPane().setLayout(new BorderLayout());
		
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panLeft,jTabbedPane);
		 JScrollPane dbnavigator=new JScrollPane(dbTree);
		    dbnavigator.setVisible(true);
		    TitledBorder dbBorder = BorderFactory.createTitledBorder("DB Explorer");
		    dbnavigator.setBorder(dbBorder);
		
		JSplitPane leftsplit=new JSplitPane(JSplitPane.VERTICAL_SPLIT,panLeft,dbnavigator);
		split.add(leftsplit);
		
		split.setDividerLocation(320);
		split.setOneTouchExpandable(true);
		leftsplit.setOneTouchExpandable(true);
		leftsplit.setDividerLocation(225);
		getContentPane().add(north,BorderLayout.NORTH);
		getContentPane().add(split,BorderLayout.CENTER);
		
		Btoolbar();
		
		getContentPane().add(btoolbar,BorderLayout.SOUTH);
		
		JScrollPane navigator=new JScrollPane(listView);
		
		navigator.setPreferredSize(new Dimension(100,100));
		
		JPanel navigator2=new JPanel();
		navigator2.setBackground(Color.WHITE);
		navigator2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		navigator2.setPreferredSize(new Dimension(100,100));
		
		panLeft.setLayout(new BorderLayout());
		//datab.add(new JLabel(Utilities.loadImageIcon("jaba",".jpg")));
		datab.setLayout(new BorderLayout());
		datab.add(new JLabel(Utilities.loadImageIcon("jaba",".jpg")));
		
		panLeft.add(navigator,BorderLayout.CENTER);
		datab.add(navigator2,BorderLayout.CENTER);
		setVisible(true);
		changeColourDB();
		dBButon.setIcon(Utilities.loadImageIcon("color_24",".png"));
	}
	
	
	private void Btoolbar(){
		btoolbar = new BottomToolbar();

	}
	private void initialiseDBTree(){
		dbTree=new DBTree();
	}
	
	private void initList(){
		listView = new ListaView();
		listView.setCellRenderer(new ListaCellRenderer());
		
		
	}
	public void updateComponent(){
		setTitle(Localisation.getInstance().getBundle().getString("appname"));
	}
	
	public ActionsManager getActionsManager() {
		return actionsManager;
	}

	
	
	public Menu getMenu() {
		return menu;
	}

	public ListaView getListView() {
		return listView;
	}

	public void setListView(ListaView listView) {
		this.listView = listView;
	}

	public FileView getFileView() {
		return fileView;
	}

	private void inittabpane(){
		jTabbedPane = new JTabbedPane();
	}
	
	public void setFileView(FileView fileView) {
		this.fileView = fileView;
		jTabbedPane.add(fileView.getUiFile().getFileName(), fileView);
	    for(int i = 0;i<jTabbedPane.getComponentCount();i++){
	    	jTabbedPane.setTabComponentAt(i, new ButtonTabComponent(jTabbedPane));
	    }
     	SwingUtilities.updateComponentTreeUI(this);
	}

	public void setFileViewInd(FileViewInd fileViewInd) {
		this.fileViewInd = fileViewInd;
		
		AppWindow.getInstance().getBtoolbar().gettField6().setText(fileViewInd.getUiFile().getCounter()+"");
		  System.out.println(fileViewInd.getUiFile().getPath());
			jTabbedPane.add(fileViewInd.getUiFile().getFileName(), fileViewInd);
			for(int i =0;i<jTabbedPane.getComponentCount();i++){
				jTabbedPane.setTabComponentAt(i, new ButtonTabComponent(jTabbedPane));
			}
		}
		
	
	
	public BottomToolbar getBtoolbar() {
		return btoolbar;
	}

	public JTabbedPane getjTabbedPane() {
		return jTabbedPane;
	}

	public PageRightClickMenu getRightclick() {
		return rightclick;
	}
	public void changeColourDB(){
		dBButon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Window parentWindow = SwingUtilities.windowForComponent(dBButon);
				JColorChooser jColorChooser = new JColorChooser();
				jColorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						try {
							datab.setBackground(jColorChooser.getColor());
							jTabbedPane.setBackground(jColorChooser.getColor());
							btoolbar.setBackground(jColorChooser.getColor());
							toolbar.setBackground(jColorChooser.getColor());
							upToolbar.setBackground(jColorChooser.getColor());
							panLeft.setBackground(jColorChooser.getColor());
							menu.setBackground(jColorChooser.getColor());
							menu.getFirstMenu().setBackground(jColorChooser.getColor());
							menu.getSettings().setBackground(jColorChooser.getColor());
							menu.getLanguages().setBackground(jColorChooser.getColor());
							menu.getThemes().setBackground(jColorChooser.getColor());
							menu.getColors().setBackground(jColorChooser.getColor());
							dBButon.setBackground(jColorChooser.getColor());
							menu.getHelp().setBackground(jColorChooser.getColor());
						//	SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
						} catch (Exception e2) {
							// TODO: handle exception
						}				
					}
				});;
				JDialog jDialog = new JDialog(parentWindow);
				jDialog.add(jColorChooser);
				jDialog.pack();
				jDialog.setVisible(true);
			}
		});
	}
	
	public JMenuItem getdBButon() {
		return dBButon;
	}

	public DriverToolbar getToolbar() {
		return toolbar;
	}
	
	public DBLogin dbLogin(){
		return dblogin;

	}

	public Connection getConn() {
		return conn;
	}



	public void setConn(Connection conn) {
		this.conn = conn;
	}



	public DBTree getDBTree() {
		return dbTree;
	}
	

	public void setFileViewDB(FileViewDATABASE fileViewDB) {
		this.fileViewDB = fileViewDB;
		jTabbedPane.add(fileViewDB.getUiFile().getFileName(), fileViewDB);
		try {
			for(int i =0;i<jTabbedPane.getComponentCount();i++){
			jTabbedPane.setTabComponentAt(i, new ButtonTabComponent(jTabbedPane));
			}
		} catch (Exception e) {
			
		}
		
	}
	
	public boolean openConnection(String serverName, String databaseName, String userName, String password) {
		boolean result = false;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String url = "jdbc:jtds:sqlserver://" + serverName + "/" + databaseName;
		
			conn = DriverManager.getConnection(url, userName, password);

			result = true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "InfoView", 1);
			e.printStackTrace();
		}
		return result;

	}


}

