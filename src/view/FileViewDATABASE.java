package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import event.UpdateBlockEvent;
import event.UpdateBlockListener;
import event.UpdateOverZoneEvent;
import event.UpdateOverZoneListener;
import model.FindResult;
import model.TabelaModel;
import model.db.UIDBFile;
import model.file.UIAbstractFile;
import model.file.UIINDFile;
import model.file.UISEKFile;
import model.file.UISERFile;
import model.tree.Node;
import model.tree.TreeCellRendered;

public class FileViewDATABASE extends JPanel
		implements UpdateBlockListener, UpdateOverZoneListener, TreeSelectionListener {

	private UIAbstractFile uiFile;
	private JTable table;

	private JTable overZoneTable;

	private JPanel panTop;
	private JTextField txtBlockSize;
	private JTextField txtFileSize;
	private JTextField txtRecordSize;
	private JTextField txtRecordNum;
	private JTextField txtBlockNum;

	private JTree indexTree;

	public FileViewDATABASE(final UIAbstractFile uiFile) {
		super();
		this.uiFile = uiFile;
		setLayout(new BorderLayout());

		panTop = new JPanel(new BorderLayout());

		try {
			this.uiFile.readHeader();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (!uiFile.getHeaderName().contains(".db")) {
			initPanParams();
		}
		initPanToolbar();

		this.uiFile.addUpdateBlockListener(this);

		table = new JTable();
		table.setModel(new TabelaModel(uiFile.getFields(), uiFile.getData()));

		JScrollPane scr = new JScrollPane(table);

		if (uiFile.getHeaderName().contains(".ind")) {

			overZoneTable = new JTable();
			overZoneTable.setModel(new TabelaModel(uiFile.getFields(), uiFile.getData()));
			JScrollPane scrOZT = new JScrollPane(overZoneTable);

			JSplitPane splitVer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scr, scrOZT);
			splitVer.setDividerLocation(400);

			DefaultTreeModel treeModel = new DefaultTreeModel(((UIINDFile) uiFile).getTree().getRootElement());
			indexTree = new JTree(treeModel);
			TreeCellRendered rendered = new TreeCellRendered();
			indexTree.setCellRenderer(rendered);
			indexTree.addTreeSelectionListener(this);

			JScrollPane scTree = new JScrollPane(indexTree);
			JSplitPane splitHor = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scTree, splitVer);
			splitHor.setDividerLocation(300);
			add(splitHor, BorderLayout.CENTER);

			((UIINDFile) (this.uiFile)).addUpdateOverZoneListener(this);
			try {
				((UIINDFile) uiFile).readOverZone();
			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {
			add(scr, BorderLayout.CENTER);
		}

	}

	private void initPanParams() {
		JPanel panParams = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panParams.add(new JLabel("f (block factor):"));
		txtBlockSize = new JTextField();
		txtBlockSize.setColumns(5);
		txtBlockSize.setText(String.valueOf(uiFile.getBLOCK_FACTOR()));
		panParams.add(txtBlockSize);
		JButton btnChangeBS = new JButton("Change f");
		btnChangeBS.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				uiFile.setBLOCK_SIZE(Integer.valueOf(txtBlockSize.getText()).longValue());
				txtBlockNum.setText(String.valueOf(uiFile.getBLOCK_NUM()));

			}

		});
		panParams.add(btnChangeBS);

		panParams.add(new JLabel("File size:"));
		txtFileSize = new JTextField();
		txtFileSize.setColumns(7);
		txtFileSize.setEnabled(false);

		txtFileSize.setText(String.valueOf(Math.ceil(uiFile.getFILE_SIZE() / 1024.0000)) + "KB");
		panParams.add(txtFileSize);

		panParams.add(new JLabel("Record size(B):"));
		txtRecordSize = new JTextField();
		txtRecordSize.setColumns(7);
		txtRecordSize.setEnabled(false);
		txtRecordSize.setText(String.valueOf(uiFile.getRECORD_SIZE()));
		panParams.add(txtRecordSize);

		panParams.add(new JLabel("Record num:"));
		txtRecordNum = new JTextField();
		txtRecordNum.setColumns(7);
		txtRecordNum.setEnabled(false);
		txtRecordNum.setText(String.valueOf(uiFile.getRECORD_NUM()));
		panParams.add(txtRecordNum);

		panParams.add(new JLabel("Block num:"));
		txtBlockNum = new JTextField();
		txtBlockNum.setColumns(7);
		txtBlockNum.setEnabled(false);
		txtBlockNum.setText(String.valueOf(uiFile.getBLOCK_NUM()));
		panParams.add(txtBlockNum);

		panParams.setBackground(new Color(153, 204, 255));
		panTop.add(panParams, BorderLayout.NORTH);

	}

	private void initPanToolbar() {
		JPanel panToolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JButton btnFetch = new JButton(!uiFile.getHeaderName().contains(".db") ? "Fetch next block" : "Refresh");
		btnFetch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiFile.setMODE(UISERFile.BROWSE_MODE);
				try {
					uiFile.fetchNextBlock();
				} catch (IOException e) {

					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (SQLException e) {

					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}

		});
		panToolbar.add(btnFetch);

		JButton btnAdd = new JButton("Add Record");
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiFile.setMODE(UISERFile.ADD_MODE);
				AUFRow addRow = new AUFRow(arg0.getActionCommand(), uiFile.getFields());

				addRow.setModal(true);
				addRow.setVisible(true);
				try {
					uiFile.addRecord(addRow.getResultRecord());
				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}

		});
		panToolbar.add(btnAdd);

		JButton btnUpdate = new JButton("Update Record");
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					ArrayList<String> selectedRow = new ArrayList<>();
					String temporary;
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "You did not select.", "Update", 1);
						return;
					}
					for (int i = 0; i < uiFile.getFields().size(); i++) {
						if (!(temporary = (String) table.getModel().getValueAt(table.getSelectedRow(), i)).equals("")) {
							selectedRow.add(temporary);
						}
					}
					UpdateStatementWindow update = new UpdateStatementWindow(arg0.getActionCommand(),
							uiFile.getFields(), uiFile, selectedRow);
					update.setModal(true);
					update.setVisible(true);
					ArrayList<String> oldValues = selectedRow;
					selectedRow = update.getResultRecord();
					if (update.getResultRecord() == null) {
						return;
					}
					if (uiFile instanceof UIDBFile) {

						((UIDBFile) uiFile).updateRecord(selectedRow, oldValues);
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}

		});

		if (uiFile.getHeaderName().contains(".db")) {

			panToolbar.add(btnUpdate);
		}

		JButton btnDelete = new JButton("Delete Record");
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiFile.setMODE(UISERFile.DELETE_MODE);

				int row = table.getSelectedRow();
				if (row == -1) {

					JOptionPane.showMessageDialog(null, "You did not select", "Info View", 1);
					return;
				}

				ArrayList<String> resultRecord = new ArrayList<String>();
				for (int col = 0; col < uiFile.getFields().size(); col++) {
					resultRecord.add((String) table.getModel().getValueAt(row, col));
				}
				try {

					uiFile.deleteRecord(resultRecord);
				} catch (IOException e) {

					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (SQLException e) {

					JOptionPane.showMessageDialog(null, e.getMessage(), "Info View", 1);
				}
			}

		});
		panToolbar.add(btnDelete);

		JButton btnFind = new JButton("Find Record");

		btnFind.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiFile.setMODE(UISERFile.FIND_MODE);
				JOptionPane.showMessageDialog(null, "Use only filter find.", "InfoView", 2);
			}

		});
		panToolbar.add(btnFind);

		if (uiFile.getHeaderName().contains(".db")) {
			JButton btnFilterFind = new JButton("Filter Find");

			btnFilterFind.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					uiFile.setMODE(UISERFile.FIND_MODE);
					FilterFindWindow findRow = new FilterFindWindow(arg0.getActionCommand(), uiFile.getFields());
					findRow.setModal(true);
					findRow.setVisible(true);
					FindResult findResult = new FindResult();

					try {
						((UIDBFile) uiFile).filterFind(findRow.getResultRecord());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Info View", 1);
					}
				}

			});
			panToolbar.add(btnFilterFind);
		}

		JButton btnSortMDI = new JButton("Sort MDI");
		btnSortMDI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				PrepareSortFile pSort = new PrepareSortFile("Select sort parametars for MDI", uiFile.getFields());
				pSort.setModal(true);
				pSort.setVisible(true);

				try {

					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					uiFile.sortMDI();
				} catch (IOException | SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Info View", 1);
					e.printStackTrace();
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			}

		});
		if (!uiFile.getHeaderName().contains(".ind")) {
			panToolbar.add(btnSortMDI);
		}

		JButton btnSortMM = new JButton("Sort MM");
		btnSortMM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				PrepareSortFile pSort = new PrepareSortFile("Select sort parametars for MM", uiFile.getFields());
				pSort.setModal(true);
				pSort.setVisible(true);

				try {

					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					uiFile.sortMM();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			}

		});
		if (!uiFile.getHeaderName().contains(".ind") && !uiFile.getHeaderName().contains(".db")) {
			panToolbar.add(btnSortMM);
		}

		JButton btnMakeSek = new JButton("Make .sek");
		btnMakeSek.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					((UISERFile) uiFile).makeSEKFile();
				} catch (IOException e) {

					JOptionPane.showMessageDialog(null, e.getMessage());
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			}

		});

		if (uiFile.getHeaderName().contains(".ser")) {

			panToolbar.add(btnMakeSek);
		}

		JButton btnMakeIND = new JButton("Make .ind");
		btnMakeIND.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					((UISEKFile) uiFile).makeINDFile();
				} catch (IOException e) {

					JOptionPane.showMessageDialog(null, e.getMessage(), "Info View", 1);
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			}

		});

		if (uiFile.getHeaderName().contains(".sek")) {

			panToolbar.add(btnMakeIND);
		}

		panTop.add(panToolbar, BorderLayout.CENTER);
		add(panTop, BorderLayout.NORTH);

	}

	public UIAbstractFile getUiFile() {
		return uiFile;
	}

	public void updateBlockPerformed(UpdateBlockEvent e) {
		table.setModel(new TabelaModel(uiFile.getFields(), uiFile.getData()));
	}

	public void updateOverZonePerformed(UpdateOverZoneEvent e) {

		overZoneTable.setModel(new TabelaModel(uiFile.getFields(), ((UIINDFile) uiFile).getOverZoneData()));

	}

	public void valueChanged(TreeSelectionEvent e) {

		Node node = (Node) e.getPath().getLastPathComponent();
		if (node.getChildCount() == 0) {

		}

	}
}
