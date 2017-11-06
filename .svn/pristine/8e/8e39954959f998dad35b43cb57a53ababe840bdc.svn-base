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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import gui.AppWindow;
import model.TabelaModel;
import model.file.UIAbstractFile;
import model.file.UIFileField;
import model.file.UISEKFile;
import model.file.UISERFile;

@SuppressWarnings("serial")
public class FindWindow extends JDialog {

	private HashMap<String, JTextField> inputFields = new HashMap<String, JTextField>();

	private ArrayList<String> resultRecord;
	private UIAbstractFile uiIFile;
	private JTable tabla;

	private JButton makeSer;
	private JButton binarySearch;
	private long initialBlockFactor;
	private int primaryKeyCounter = 0;

	public FindWindow(String title, final ArrayList<UIFileField> fields, UIAbstractFile uiFile, JTable table) {
		super();
		super.setTitle(title);

		resultRecord = new ArrayList<String>();
		uiIFile = uiFile;
		tabla = table;
		initialBlockFactor = uiIFile.getBLOCK_FACTOR();

		setLayout(new GridLayout(fields.size() + 1, 1, 10, 10));

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

		Box boxC = new Box(BoxLayout.X_AXIS);

		JButton findStart = new JButton("Find first");
		findStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiIFile.setCounter(uiFile.getCounter() + 1);
				AppWindow.getInstance().getBtoolbar().gettField6().setText(uiFile.getCounter() + "");
				restoreBlockFactor();
				resultRecord.clear();
				makeSer.setEnabled(false);

				for (int i = 0; i < fields.size(); i++) {
					resultRecord.add(getFieldValue(fields.get(i).getFieldName()));
				}

				if (!resultRecord.isEmpty()) {
					int[] position = new int[1];
					position[0] = -1;

					if (!uiIFile.findRecord(resultRecord, position, true)) {
						JOptionPane.showMessageDialog(null, "Trazeni slog nije pronadjen.", "Info View", 1);
						tabla.getSelectionModel().setSelectionInterval(position[0], position[0]);
					} else {
						tabla.getSelectionModel().setSelectionInterval(position[0], position[0]);
					}
				}
				setVisible(true);
			}

		});

		boxC.add(findStart);

		JButton findAll = new JButton("Find all");
		findAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				makeSer.setEnabled(false);
				resultRecord.clear();
				uiIFile.setCounter(uiFile.getCounter() + 1);
				AppWindow.getInstance().getBtoolbar().gettField6().setText(uiFile.getCounter() + "");
				for (int i = 0; i < fields.size(); i++) {
					resultRecord.add(getFieldValue(fields.get(i).getFieldName()));
				}

				if (!resultRecord.isEmpty()) {

					int[] position = new int[1];
					position[0] = -1;
					String[][] bulkSearch = uiIFile.findAll(resultRecord, position);

					if (bulkSearch == null) {
						JOptionPane.showMessageDialog(null, "Trazeni slog nije pronadjen.", "Info View", 1);
						tabla.getSelectionModel().setSelectionInterval(position[0], position[0]);
					} else {
						tabla.setModel(new TabelaModel(uiIFile.getFields(), bulkSearch));
						makeSer.setEnabled(true);
					}
				}
				setVisible(true);
			}

		});
		if (uiIFile instanceof UISERFile) {
			boxC.add(findAll);

		}

		makeSer = new JButton("MakeSerFile");
		makeSer.setEnabled(false);
		makeSer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				MakeWindow createSer = new MakeWindow((UISERFile) uiIFile);
				createSer.setVisible(true);
				makeSer.setEnabled(false);
			}

		});
		if (uiIFile instanceof UISERFile) {
			boxC.add(makeSer);

		}

		binarySearch = new JButton("Binary Search");
		binarySearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiIFile.setCounter(uiFile.getCounter() + 1);
				AppWindow.getInstance().getBtoolbar().gettField6().setText(uiFile.getCounter() + "");
				resultRecord.clear();
				primaryKeyCounter = 0;

				for (int i = 0; i < fields.size(); i++) {
					resultRecord.add(getFieldValue(fields.get(i).getFieldName()));
				}

				if (!resultRecord.isEmpty() && primaryKeyCounter == 2) {

					if (((UISEKFile) uiIFile).binaryAlgoritm(resultRecord)) {
						tabla.getSelectionModel().setSelectionInterval(0, 0);
					} else {
						JOptionPane.showMessageDialog(null, "Trazeni slog nije pronadjen.", "Info View", 1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Potrebni su binarni kljucevi.", "Info View", 1);
				}
				setVisible(true);
			}

		});
		if (uiIFile instanceof UISEKFile) {
			boxC.add(binarySearch);
		}

		JButton findNext = new JButton("Find next");
		findNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiIFile.setCounter(uiFile.getCounter() + 1);
				AppWindow.getInstance().getBtoolbar().gettField6().setText(uiFile.getCounter() + "");
				restoreBlockFactor();
				resultRecord.clear();
				makeSer.setEnabled(false);

				for (int i = 0; i < fields.size(); i++) {
					resultRecord.add(getFieldValue(fields.get(i).getFieldName()));
				}

				if (!resultRecord.isEmpty()) {
					int[] position = new int[1];
					position[0] = -1;

					if (!uiIFile.findRecord(resultRecord, position, false)) {
						JOptionPane.showMessageDialog(null,
								"Trazeni slog nije pronadjen.Stigli smo do kraja ove datoteke", "Info View", 1);
						tabla.getSelectionModel().setSelectionInterval(position[0], position[0]);
					} else {
						tabla.getSelectionModel().setSelectionInterval(position[0], position[0]);
					}
				}
				setVisible(true);
			}

		});
		if (uiIFile instanceof UISERFile) {
			boxC.add(findNext);
		}
		boxC.add(Box.createHorizontalGlue());

		add(boxC);
		pack();
		setLocationRelativeTo(null);

	}

	private String getFieldValue(String fieldName) {

		String fieldValue = inputFields.get(fieldName).getText();

		if (inputFields.get(fieldName).getBackground().equals(Color.GRAY) && fieldValue.length() >= 1) {
			primaryKeyCounter++;
		}

		if (fieldValue.length() > inputFields.get(fieldName).getColumns()) {
			return fieldValue.substring(0, inputFields.get(fieldName).getColumns());
		}

		for (int i = inputFields.get(fieldName).getText().length(); i < inputFields.get(fieldName).getColumns(); i++) {
			fieldValue = fieldValue + ' ';
		}

		return fieldValue;
	}

	private void restoreBlockFactor() {
		uiIFile.setBLOCK_SIZE(initialBlockFactor);
	}

}
