package gui;

import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.omg.PortableInterceptor.DISCARDING;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExtPick extends JDialog{

	private JComboBox<String> box;
	
	public ExtPick() {
		initFrame();
		initElem();
	}
	
	public void initFrame(){
		setTitle("Extension Pick");
		setSize(300,130);
		setResizable(false);
			
	}
	
	public void initElem(){
		getContentPane().setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		
		JPanel tpanel = new JPanel();
		
		JLabel lblChoseExtensions = new JLabel("Chose Extensions");
		tpanel.add(lblChoseExtensions);
		
		JCheckBox chckbxtxt = new JCheckBox(".txt");
		JCheckBox chckbxsrc = new JCheckBox(".src");
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxtxt.isSelected()){
					/*------------------------
					 * Kada se odabere .txt extenzija
					 ------------------------*/
					
					//System.out.println("TXT");
					
					if(chckbxsrc.isSelected()){
						/*------------------------
						 * Kada se odabere .src i .txt extenzija
						 ------------------------*/
						
						//System.out.println("SRC");
					}
					
				}
				else if(chckbxsrc.isSelected()){
					/*------------------------
					 * Kada se odabere .src extenzija
					 ------------------------*/
					
					//System.out.println("SRC");
				}
					
				else {
					System.out.println("No ext Selected");
				}
			}		
				//dispose();
		});
		
	
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		panel_1.add(tpanel);
		//panel_2.add(chckbxtxt);
		//panel_2.add(chckbxsrc);
		panel_3.add(btnNewButton);
		panel_3.add(btnCancel);
		
		box = new JComboBox<>();
		box.addItem("ALL");
		box.addItem(".txt");
		box.addItem(".ser");
		box.addItem(".rar");
		box.addItem("Diectory");
		panel_2.add(box);
		SwingUtilities.updateComponentTreeUI(this);

	}
		public JComboBox<String> getBox() {
			return box;
}
	
}
