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

import model.file.UIAbstractFile;
import model.file.UIFileField;
import model.file.UISEKFile;

@SuppressWarnings("serial")
public class EditWindow extends JDialog {

	private ArrayList<String> values;
	private UISEKFile uiSEKFile;

	private HashMap<String, JTextField> inputFields = new HashMap<String, JTextField>();

	private ArrayList<String> resultRecord;

	public EditWindow(String title, final ArrayList<UIFileField> fields, UISEKFile uiSekFile,
			ArrayList<String> fieldValues) {
		super();
		super.setTitle(title);

		values = fieldValues;
		uiSEKFile = uiSekFile;

		setLayout(new GridLayout(fields.size() + 1, 1));
		ArrayList<JPanel> boxes = new ArrayList<JPanel>();

		for (int i = 0; i < fields.size(); i++) {
			inputFields.put(fields.get(i).getFieldName(), new JTextField(fields.get(i).getFieldLength()));

			boxes.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
			boxes.get(i).add(new JLabel(" " + fields.get(i).getFieldName()));
			boxes.get(i).add(inputFields.get(fields.get(i).getFieldName()));
			if (fields.get(i).isFieldPK()) {
				inputFields.get(fields.get(i).getFieldName()).setBackground(Color.GRAY);
				inputFields.get(fields.get(i).getFieldName()).setForeground(Color.WHITE);
			}
			add(boxes.get(i));

		}

		for (int i = 0; i < fields.size(); i++) {
			String tmp = values.get(i);
			if (tmp != null) {
				inputFields.get(fields.get(i).getFieldName()).setText(tmp);
			}
		}

		Box boxC = new Box(BoxLayout.X_AXIS);
		JButton btnOK = new JButton("ok");
		btnOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				resultRecord = new ArrayList<String>();
				for (int i = 0; i < fields.size(); i++) {
					resultRecord.add(getFieldValue(fields.get(i).getFieldName()));
				}
				setVisible(false);
			}

		});
		boxC.add(Box.createHorizontalGlue());
		boxC.add(btnOK);
		add(boxC);
		pack();
		setLocationRelativeTo(null);

	}

	private String getFieldValue(String fieldName) {
		String fieldValue = inputFields.get(fieldName).getText();

		if (fieldValue.length() > inputFields.get(fieldName).getColumns())
			return fieldValue.substring(0, inputFields.get(fieldName).getColumns());

		for (int i = inputFields.get(fieldName).getText().length(); i < inputFields.get(fieldName).getColumns(); i++)
			fieldValue = fieldValue + ' ';
		return fieldValue;
	}

	public ArrayList<String> getResultRecord() {
		return resultRecord;
	}

}
