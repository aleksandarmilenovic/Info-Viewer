package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import gui.AppWindow;
import model.file.UISEKFile;

public class MergeWindow extends JDialog {

	private UISEKFile uiSEKFile;
	private JButton yes;
	private JButton cancel;

	public MergeWindow(UISEKFile sekFile) {

		uiSEKFile = sekFile;

		initDialog();
		initBtns();
		addAllToWindow();

		pack();
		setLocationRelativeTo(AppWindow.getInstance());

	}

	private void initDialog() {
		setTitle("Save ");
		setName("Save ");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModal(true);
	}

	private void initBtns() {

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					uiSEKFile.deleteChangeData();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});

		yes = new JButton("Yes");
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void addAllToWindow() {

		JPanel dialogPanel = new JPanel();

		JPanel createPanel = new JPanel(new BorderLayout());
		Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
		createPanel.setBorder(emptyBorder);

		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JLabel serFileText = new JLabel("Do you want to keep rows with commands?");

		southPanel.add(yes);
		southPanel.add(cancel);

		createPanel.add(serFileText, BorderLayout.CENTER);
		createPanel.add(southPanel, BorderLayout.SOUTH);

		dialogPanel.add(createPanel);

		setContentPane(dialogPanel);
	}
}
