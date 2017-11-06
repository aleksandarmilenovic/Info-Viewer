package view;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import gui.AppWindow;
import model.file.UISERFile;

public class MakeWindow extends JDialog {

	private UISERFile uiSERFile;
	private JTextField fileName;
	private JTextField uiSerFileName;
	private JButton make;
	private JButton cancel;

	public MakeWindow(UISERFile serFile) {

		uiSERFile = serFile;

		initDialog();
		fields();
		initBtns();
		addAllToWindow();

		pack();
		setLocationRelativeTo(AppWindow.getInstance());

	}

	private void initDialog() {
		setTitle("Make sek File");
		setName("Make sek File");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModal(true);
	}

	private void fields() {
		fileName = new JTextField();

		uiSerFileName = new JTextField();

	}

	private void initBtns() {
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		make = new JButton("Make");
		make.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiSerFileName.getText().length() > 0 && fileName.getText().length() > 0) {
					try {
						setCursor(new Cursor(Cursor.WAIT_CURSOR));
						uiSERFile.fileFromSearches(fileName.getText() + ".txt", uiSerFileName.getText() + ".ser");
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				}
				dispose();
			}
		});
	}

	private void addAllToWindow() {

		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

		JPanel createPanel = new JPanel();

		JPanel centerPanel = new JPanel(new GridLayout(0, 2, 10, 10));

		JLabel dataText = new JLabel("Data file:");
		centerPanel.add(dataText);
		centerPanel.add(fileName);

		JLabel serFileText = new JLabel("Ser file:");
		centerPanel.add(serFileText);
		centerPanel.add(uiSerFileName);

		centerPanel.add(cancel);
		centerPanel.add(make);

		createPanel.add(centerPanel);

		dialogPanel.add(createPanel);

		setContentPane(dialogPanel);
	}

}
