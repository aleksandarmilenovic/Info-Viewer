package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import actions.OpenDriverAction;
import values.Icons;

import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class BottomToolbar extends JToolBar{

	private JButton colorc;
	private JPanel sPanels;
	
	private JLabel tLabel1;
	private JLabel tLabel2;
	private JLabel tLabel3;
	
	private JTextField tField1;
	private JTextField tField2;
	private JTextField tField3;
	private JTextField tField4;
	private JTextField tField5;
	private JTextField tField6;
	private JButton remove = new JButton();
	
	private JComboBox<String> box;
	
	
	public BottomToolbar() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(SystemColor.controlHighlight);
		initPanel();
		initSPanels();
		}
	
		public void initPanel(){
			Dimension size= getPreferredSize();
			size.height=25;
			setPreferredSize(new Dimension(504, 41));
			
		}
		public void initSPanels(){
			sPanels=new JPanel();
			FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 6, 4);
			setLayout(flowLayout);
			textArea();
			
			
		}
		
		
		
		public void textArea(){
			
			box = new JComboBox<>();
			box.addItem("ALL");
			box.addItem(".txt");
			box.addItem(".ser");
			box.addItem(".sek");
			box.addItem(".ind");
			box.addItem("Diectory");
			box.addItem("Directory and .txt");
			box.addItem("Directory and .ser");
			box.addItem("Directory and .sek");
			box.addItem("Directory and .ind");
			add(box);
			add(AppWindow.getInstance().getActionsManager().getDeleteExt());
		//	remove.setIcon(Icons.NEW_FOLDER);
		/*	remove.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					box.removeItem(box.getSelectedItem());
					SwingUtilities.updateComponentTreeUI(box);
				}
			});*/
		//	add(remove);
			SwingUtilities.updateComponentTreeUI(this);
			
		   
			
			JLabel tLabel1=new JLabel("Name: ");
			add(tLabel1);
			
			
			tField1 = new JTextField();
			tField1.setBackground(SystemColor.menu);
			tField1.setEditable(false);
			add(tField1);
			tField1.setColumns(10);
			
			JLabel tLabel2 = new JLabel("Path: ");
			add(tLabel2);
			
			tField2 = new JTextField();
			tField2.setBackground(SystemColor.menu);
			tField2.setEditable(false);
			add(tField2);
			tField2.setColumns(10);
			
			JLabel tLabel3 = new JLabel("Size: ");
			add(tLabel3);
			
			tField3 = new JTextField();
			tField3.setBackground(SystemColor.menu);
			tField3.setEditable(false);
			add(tField3);
			tField3.setColumns(7);
			
			JLabel created = new JLabel("Created: ");
			add(created);
			
			tField4 = new JTextField();
			tField4.setEditable(false);
			add(tField4);
			tField4.setColumns(11);
			
			JLabel modified = new JLabel("Modified: ");
			add(modified);
			
			tField5 = new JTextField();
			tField5.setEditable(false);
			add(tField5);
			tField5.setColumns(11);
			
			tField6 = new JTextField(3);
			tField6.setEditable(false);
			add(tField6);
		}
		
		public void addNewExtentions(String name){
			box.addItem(name);
		}
		
		
		public JTextField gettField1() {
			return tField1;
		}

		public JTextField gettField2() {
			return tField2;
		}

		public JTextField gettField3() {
			return tField3;
		}

		public JTextField gettField4() {
			return tField4;
		}
		
		public JTextField gettField5() {
			return tField5;
		}
		public JComboBox<String> getBox() {
			return box;
		}

		public JTextField gettField6() {
			return tField6;
		}
		
}
