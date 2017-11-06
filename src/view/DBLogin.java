package view;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import gui.AppWindow;
import helper_classes.Utilities;
import jsonTABLE.DataBaseTable;
import model.db.DBMetadata;
import model.db.DBNode;

@SuppressWarnings("serial")
public class DBLogin extends JDialog{
	private JLabel lblServerName=new JLabel("Server Name:     ");
	private JLabel lblDatabaseName=new JLabel("Database Name:");
	private JLabel lblAuth=new JLabel("Authentication:");
	JRadioButton rbtnWindows = new JRadioButton("Windows");
	JRadioButton rbtnSQL= new JRadioButton("SQL Server");

	
	private JLabel lblUserName=new JLabel("Login:                   ");
	private JLabel lblPassword=new JLabel("Password:           ");
	private JTextField txtServerName=new JTextField(20);
	private JTextField txtDatabaseName=new JTextField(20);
	private JTextField txtUserName=new JTextField(10);
	private JPasswordField txtPassword=new JPasswordField(10);
	private JButton btnOk=new JButton("Connect");
	private JButton btnCancel=new JButton("Cancel");
	
	public boolean connect=false;
	
	
	public DBLogin() {
		super();
		getContentPane().setLayout(new GridLayout(6,1));
		JPanel pnl1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl1.setBackground(SystemColor.inactiveCaption);
		pnl1.add(lblServerName);
		txtServerName.setBackground(SystemColor.menu);
		pnl1.add(txtServerName);
		getContentPane().add(pnl1);
		txtServerName.setText("147.91.175.155");
		
		
		JPanel pnl2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl2.setBackground(SystemColor.inactiveCaption);
		pnl2.add(lblDatabaseName);
		txtDatabaseName.setBackground(SystemColor.menu);
		pnl2.add(txtDatabaseName);
		getContentPane().add(pnl2);
		txtDatabaseName.setText("ui-2015-tim201.4");
		ButtonGroup group = new ButtonGroup();
		
		JPanel pnl4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl4.setBackground(SystemColor.inactiveCaption);
		pnl4.add(lblUserName);
		txtUserName.setBackground(SystemColor.menu);
		pnl4.add(txtUserName);
		getContentPane().add(pnl4);
		txtUserName.setText("ui-2015-tim201.4");
		
		JPanel pnl5=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl5.setBackground(SystemColor.inactiveCaption);
		pnl5.add(lblPassword);
		txtPassword.setBackground(SystemColor.menu);
		pnl5.add(txtPassword);
		txtPassword.setEchoChar('*');
		getContentPane().add(pnl5);
		txtPassword.setText("ui-2015-tim201.4.91bomi1");
		
		JPanel pnl6=new JPanel();
		pnl6.setBackground(SystemColor.inactiveCaption);
		
		JPanel pnl3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl3.setBackground(SystemColor.inactiveCaption);
		pnl3.add(lblAuth);
		group.add(rbtnWindows);
		group.add(rbtnSQL);
		rbtnWindows.setBackground(SystemColor.inactiveCaption);
		pnl3.add(rbtnWindows);
		rbtnSQL.setBackground(SystemColor.inactiveCaption);
		pnl3.add(rbtnSQL);
		getContentPane().add(pnl3);
		
		rbtnSQL.setSelected(true);
		rbtnWindows.setEnabled(false);
		rbtnSQL.setEnabled(false);
		
		getContentPane().add(pnl6);
		pnl6.setLayout(null);
		btnCancel.setBounds(10, 11, 86, 23);
		btnCancel.setFont(UIManager.getFont("Button.font"));
		btnCancel.setBackground(SystemColor.inactiveCaption);
		pnl6.add(btnCancel);
		btnCancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				connect=false;
			}
			
		});
		btnOk.setBounds(283, 11, 101, 23);
		btnOk.setFont(btnOk.getFont().deriveFont(btnOk.getFont().getStyle() | Font.BOLD));
		btnOk.setBackground(SystemColor.inactiveCaption);
		pnl6.add(btnOk);
		
		btnOk.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				connect=true;
				DBLogin();
				setVisible(false);
				
			}
			
		});
		setIconImage(Utilities.loadBufferedImage("connect_24", ".png"));
		setModal(true);
		setResizable(false);
		setSize(400,300);
		setLocationRelativeTo(null);
		setTitle("Connect to Server");
		setVisible(true);
	
		
	}

	public JLabel getLblServerName() {
		return lblServerName;
	}

	public void setLblServerName(JLabel lblServerName) {
		this.lblServerName = lblServerName;
	}

	public JLabel getLblAuth() {
		return lblAuth;
	}

	public void setLblAuth(JLabel lblAuth) {
		this.lblAuth = lblAuth;
	}

	public JLabel getLblUserName() {
		return lblUserName;
	}

	public void setLblUserName(JLabel lblUserName) {
		this.lblUserName = lblUserName;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public JTextField getTxtServerName() {
		return txtServerName;
	}

	public void setTxtServerName(JTextField txtServerName) {
		this.txtServerName = txtServerName;
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public JTextField getTxtDatabaseName() {
		return txtDatabaseName;
	}

	public void setTxtDatabaseName(JTextField txtDatabaseName) {
		this.txtDatabaseName = txtDatabaseName;
	}
	private void DBLogin(){
		if (connect==true){
		
			String serverName =  this.txtServerName.getText();
			String databaseName = this.txtDatabaseName.getText();
			String userName = this.txtUserName.getText();
			char[] pass = this.txtPassword.getPassword();
			System.out.println("Server"+this.txtServerName.getText()+"DATABASE"+ this.txtDatabaseName.getText() +"USERNAME"+this.txtUserName.getText()+"PASSWORD"+this.txtPassword.getText());
			String password = new String(pass);
			
			if (AppWindow.getInstance().openConnection(serverName,databaseName, userName, password)){
				
				DBNode dbRootNode = (DBNode) AppWindow.getInstance().getDBTree().getModel().getRoot();
				dbRootNode.setName("Server:"+serverName+", Dabase:"+ databaseName);
				SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
				DBMetadata dbMetaData = new DBMetadata();
				initTableNames("metadata.json");
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					dbMetaData.readDatabase();
				} catch (Exception e) {
					
					e.printStackTrace();
				}finally{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			 }
			
		}
	}
	
	private void initTableNames(String path){
		try {
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
			JSONTokener jsonTokener = new JSONTokener(bufferedreader);
			JSONArray jsonArray = new JSONArray(jsonTokener);
			bufferedreader.close();
			DataBaseTable.setDataBaseTable(jsonArray);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
	
	

}
