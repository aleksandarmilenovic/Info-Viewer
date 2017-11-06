package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import gui.AppWindow;
import helper_classes.Utilities;

public class Main {

	public static void main(String[] args) throws MalformedURLException {
		/*
		JWindow window = new JWindow();
		window.getContentPane().add(new JLabel("", (Utilities.loadImageIcon("jaba",".jpg")), SwingConstants.CENTER));
		window.setBounds(500, 150, 700, 700);
		window.setVisible(true);
		try {
		    Thread.sleep(2500);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		window.setVisible(false);
		window.dispose();
		*/
		
		lScreen();
		
		
		AppWindow windows = AppWindow.getInstance();
		

	}

	private static void lScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		JProgressBar bar = new JProgressBar();
		bar.setValue(0);
		bar.setStringPainted(true);

		JWindow window = new JWindow();
		window.getContentPane().add(new JLabel("", Utilities.loadImageIcon("jaba",".png"), SwingConstants.CENTER));
		window.setOpacity(1f);
		window.getContentPane().add(bar, BorderLayout.SOUTH);
		window.setBounds((int)width/2-250, (int)height/2-225, 450, 500);
		//window.setLocationRelativeTo(null);
		window.setVisible(true);
		try {
			Thread.sleep(300);
			bar.setString("Init");
			Thread.sleep(900);
			bar.setString(null);
			bar.setValue(25);
			Thread.sleep(300);
			bar.setString("Loading");
			Thread.sleep(500);
			bar.setString(null);
			bar.setValue(40);
			Thread.sleep(300);
			bar.setString("Applying");
			Thread.sleep(700);
			bar.setString(null);
			bar.setValue(50);
			Thread.sleep(400);
			bar.setString("Confgigs app");
			Thread.sleep(500);
			bar.setString(null);
			bar.setValue(70);
			Thread.sleep(300);
			bar.setValue(80);
			Thread.sleep(300);
			bar.setString("Startting app");
			Thread.sleep(600);
			bar.setString(null);
			bar.setValue(100);
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		window.setVisible(false);
		
	}

}
