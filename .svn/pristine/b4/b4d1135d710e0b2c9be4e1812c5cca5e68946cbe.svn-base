package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import helper_classes.Utilities;
import language.Localisation;
import java.awt.GridLayout;
import java.awt.Image;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;
import java.awt.Color;

public class AboutDialog extends JDialog{
	private JLabel jLabel;
	private JLabel jLabel2;
	public AboutDialog() {
		initPane();
		initDialog();
		pack();
	}
	
	public void initDialog(){
	//	setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle(Localisation.getInstance().getBundle().getString("aboutaction"));
		setSize(new Dimension(450, 500));
		setResizable(false);
		setLocationRelativeTo(AppWindow.getInstance());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public void initPane(){
		JPanel pane =new JPanel();
		
		JPanel down=new JPanel();
		pane.setLayout(new GridLayout(2, 0));

		setContentPane(pane);
		
		JPanel TeamLogo = new JPanel();
		pane.add(TeamLogo);
		TeamLogo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 55));
		TeamLogo.add(new JLabel(Utilities.loadImageIcon("logo", ".png")));
		
		
		JPanel MemberInfo = new JPanel();
		pane.add(MemberInfo);
		MemberInfo.setLayout(new GridLayout(2, 0));
		jLabel = new JLabel((Utilities.loadImageIcon("C3PO", ".png")));
	    jLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				JFrame jFrame = new JFrame("About");
				JPanel jPanel = new JPanel();
			    jFrame.setLocationRelativeTo(null);
			    jFrame.setSize(200, 200);
			    jPanel.add(new JLabel(Utilities.loadImageIcon("milenovic", ".jpg")));
			    jFrame.add(jPanel);
			    jFrame.setVisible(true);
				jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jFrame.setAlwaysOnTop(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		MemberInfo.add(jLabel);
		MemberInfo.add(new JLabel(Utilities.loadImageIcon("R2D2", ".png")));
		
		JPanel panel_1 = new JPanel();
		MemberInfo.add(panel_1);
		
		JLabel aca = new JLabel("Aleksandar Milenovic RN64-2015");
		panel_1.add(aca);
		
		JPanel panel_2 = new JPanel();
		MemberInfo.add(panel_2);
		
		JLabel ja = new JLabel("Bogdan Veljkoic RN67-2015");
		panel_2.add(ja);
	}

}
