package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import language.Localisation;
import model.ListaModel;
import model.UIFile;

public class FileView extends JPanel implements ActionListener,KeyListener{

	private UIFile uiFile;
	private JTextArea area;
	private JButton btnSave;
//	private JTabbedPane tabbedPane;
	
	public FileView(UIFile file) {
		super();
		this.uiFile=file;
		setLayout(new BorderLayout());
		JPanel topPanel=new JPanel();
		btnSave=new JButton("Save");
		btnSave.setEnabled(false);

		btnSave.addActionListener(this);
		topPanel.add(btnSave);
		add(topPanel,BorderLayout.NORTH);
		area=new JTextArea();
		area.addKeyListener(this);
		area.setBackground(Color.WHITE);
		area.setText(file.getContent().toString());
		JScrollPane scroll=new JScrollPane(area);
		add(scroll);
	//	add(area);
	//	tabbedPane = new JTabbedPane();
	//	tabbedPane.add(file.getFileName().toString(), area);
	//	add(tabbedPane);
		SwingUtilities.updateComponentTreeUI(area);
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		btnSave.setEnabled(true);
		//uiFile.saveFile(area.getText());
		//SwingUtilities.updateComponentTreeUI(area);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		uiFile.saveFile(area.getText());
		btnSave.setEnabled(false);
	//	SwingUtilities.updateComponentTreeUI(area);
	}
//	public JTabbedPane getTabbedPane() {
//		return tabbedPane;
//	}


	public UIFile getUiFile() {
		return uiFile;
	}


	public JButton getBtnSave() {
		return btnSave;
	}
		
}
