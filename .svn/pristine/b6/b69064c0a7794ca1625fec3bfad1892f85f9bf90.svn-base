package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.file.UIFileField;

@SuppressWarnings("serial")
public class FilterFindWindow extends JDialog {

	private HashMap<String, JTextField> inputFields = new HashMap<String, JTextField>();

	private ArrayList<String> resultRecord;

	public FilterFindWindow(String title, final ArrayList<UIFileField> fields) {
		super();
		super.setTitle(title);
		setLayout(new GridLayout(fields.size() + 1, 1));
		ArrayList<JPanel> boxes = new ArrayList<JPanel>();

		for (int i = 0; i < fields.size(); i++) {
			inputFields.put(fields.get(i).getFieldName(), new JTextField(fields.get(i).getFieldLength()));

			boxes.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
			String name = " " + fields.get(i).getFieldName();
			
			boxes.get(i).add(new JLabel(name));
			boxes.get(i).add(inputFields.get(fields.get(i).getFieldName()));

			if (fields.get(i).isFieldPK()) {
				inputFields.get(fields.get(i).getFieldName()).setBackground(Color.GRAY);
				inputFields.get(fields.get(i).getFieldName()).setForeground(Color.WHITE);
			}
			add(boxes.get(i));

		}

		Box box = new Box(BoxLayout.X_AXIS);
		JButton getResaults = new JButton("Get resaults");
		getResaults.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				resultRecord = new ArrayList<String>();
				for (int i = 0; i < fields.size(); i++) {
					resultRecord.add(getFieldValue(fields.get(i).getFieldName()));
				}
				setVisible(false);
			}

		});
		box.add(Box.createHorizontalGlue());
		box.add(getResaults);
		add(box);
		pack();
		setLocationRelativeTo(null);

	}

	private String getFieldValue(String fieldName) {

		return inputFields.get(fieldName).getText();
	}

	public ArrayList<String> getResultRecord() {
		return resultRecord;
	}

}
