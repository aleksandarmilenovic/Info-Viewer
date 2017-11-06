package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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

import constants.ExternalSort;
import model.TabelaModel;
import model.file.UIAbstractFile;
import model.file.UIINDFile;
import model.file.UISEKFile;
import model.file.UISERFile;
import model.tree.Node;
import model.tree.NodeElement;
import model.tree.TreeCellRendered;
import event.UpdateBlockEvent;
import event.UpdateBlockListener;
import gui.AppWindow;

@SuppressWarnings("serial")
public class FileViewInd extends JPanel implements UpdateBlockListener, TreeSelectionListener, Observer {

	private UIAbstractFile uiFile;
	private JTable table;
	private JTable overZoneTable;

	private JPanel panTop;
	private JTextField blockFactorTextField;
	private JTextField txtBlockSize;
	private JTextField txtFileSize;
	private JTextField txtRecordSize;
	private JTextField txtRecordNum;
	private JTextField txtBlockNum;
	private JScrollPane scr;
	private JTree indexTree;

	public FileViewInd(final UIAbstractFile uiFile) {
		super();
		setLayout(new BorderLayout());

		this.uiFile = uiFile;
		if (uiFile instanceof UISEKFile) {
			((UISEKFile) uiFile).initChangeData();
		}

		try {
			this.uiFile.readHeader();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.uiFile.addUpdateBlockListener(this);

		panTop = new JPanel(new BorderLayout());
		initPanParams();
		initPanToolbar();

		table = new JTable();
		table.setModel(new TabelaModel(uiFile.getFields(), uiFile.getData()));
		scr = new JScrollPane(table);
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
			// panTop.setBackground(Color.RED);

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

		if (uiFile instanceof UISERFile) {
			panParams.setBackground(Color.YELLOW);
		} else if (uiFile instanceof UISEKFile) {
			panParams.setBackground(Color.GREEN);
		} else if (uiFile instanceof UIINDFile) {
			panParams.setBackground(Color.RED);
		}

	}

	private void initPanToolbar() {
		JPanel blockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		blockPanel.add(new JLabel("BLOCK FACTOR :"));
		blockFactorTextField = new JTextField();
		blockFactorTextField.setColumns(5);
		blockFactorTextField.setText(String.valueOf(uiFile.getBLOCK_FACTOR()));
		blockPanel.add(blockFactorTextField);

		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiFile.setBLOCK_SIZE(Integer.valueOf(blockFactorTextField.getText()).longValue());

			}
		});

		blockPanel.add(applyButton);
		blockPanel.setBackground(new Color(200, 221, 242));

		JPanel fetchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		fetchPanel.setBackground(new Color(200, 221, 242));

		JButton fetchButton = new JButton("Fetch next block");
		fetchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiFile.setMODE(UISERFile.BROWSE_MODE);
				try {
					uiFile.fetchNextBlock();
					repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		fetchPanel.add(fetchButton);

		JButton btnAdd = new JButton("Add Record");
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiFile.setMODE(UISERFile.ADD_MODE);
				AUFRow addRow = new AUFRow(arg0.getActionCommand(), uiFile.getFields());

				addRow.setModal(true);
				addRow.setVisible(true);

				if (addRow.getResultRecord() == null) {
					addRow.dispose();
					return;
				}

				try {
					if (uiFile instanceof UISEKFile) {
						((UISEKFile) uiFile).addByOrder(addRow.getResultRecord());
					} else {
						uiFile.addRecord(addRow.getResultRecord());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		fetchPanel.add(btnAdd);

		JButton btnDelete = new JButton("Delete Record");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> selectedRow = new ArrayList<>();
				String tmp;
				for (int i = 0; i < uiFile.getFields().size(); i++) {
					if (!(tmp = (String) table.getModel().getValueAt(table.getSelectedRow(), i)).equals("")) {
						selectedRow.add(tmp);
					}
				}

				try {
					uiFile.deleteRecord(selectedRow);
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		fetchPanel.add(btnDelete);

		JButton btnFind = new JButton("Find Record");

		btnFind.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				uiFile.setMODE(UISERFile.FIND_MODE);
				FindWindow findRow = new FindWindow(arg0.getActionCommand(), uiFile.getFields(), uiFile, table);
				findRow.setModal(true);
				findRow.setVisible(true);

			}

		});

		fetchPanel.add(btnFind);

		JButton btnEditRec = new JButton("Edit rec");
		btnEditRec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					ArrayList<String> selectedRow = new ArrayList<>();
					String tmp;
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Nijedan red nije selektovan.", "Info View", 1);
						return;
					}
					for (int i = 0; i < uiFile.getFields().size(); i++) {
						if (!(tmp = (String) table.getModel().getValueAt(table.getSelectedRow(), i)).equals("")) {
							selectedRow.add(tmp);
						}
					}
					EditWindow update = new EditWindow(arg0.getActionCommand(), uiFile.getFields(), (UISEKFile) uiFile,
							selectedRow);
					update.setModal(true);
					update.setVisible(true);
					selectedRow = update.getResultRecord();
					if (update.getResultRecord() == null) {
						return;
					}
					((UISEKFile) uiFile).editRecord(selectedRow);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}

		});

		if (uiFile.getHeaderName().contains(".sek")) {

			fetchPanel.add(btnEditRec);
		}

		JButton btnSortMDI = new JButton("Sort MDI");
		btnSortMDI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				PrepareSortFile pSort = new PrepareSortFile("Select sort parametars for MDI", uiFile.getFields());
				pSort.setModal(true);
				pSort.setVisible(true);

				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					uiFile.sortMDI(uiFile.getFileName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			}

		});
		fetchPanel.add(btnSortMDI);

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			}

		});

		JButton btnSortExt = new JButton("External sort");
		btnSortExt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(scr);
				uiFile.removeUpdateBlockListener(FileViewInd.this);
				revalidate();
				repaint();

				ExternalSort sortWorker = new ExternalSort((UISERFile) uiFile);

				sortWorker.addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if ("progress".equals(evt.getPropertyName())) {
							System.out.println("RADIM");
						} else if (sortWorker.isDone()) {
							add(scr, BorderLayout.CENTER);
							uiFile.addUpdateBlockListener(FileViewInd.this);
							revalidate();
							repaint();
						}
					}
				});
				sortWorker.execute();
			}
		});
		if (uiFile.getHeaderName().contains(".ser")) {

			fetchPanel.add(btnSortExt);
		}

		JButton updateWhole = new JButton("Update SEK");
		updateWhole.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					((UISEKFile) uiFile).updateSEKFile();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		if (uiFile.getHeaderName().contains(".sek")) {
			fetchPanel.add(updateWhole);
		}

		fetchPanel.add(btnSortMM);

		JButton btnMakeSek = new JButton("Make .sek");
		btnMakeSek.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					((UISERFile) uiFile).makeSEKFile();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(AppWindow.getInstance(), "Niste fecovali podatke");

				} finally {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			}

		});

		if (uiFile.getHeaderName().contains(".ser")) {
			fetchPanel.add(btnMakeSek);
			fetchPanel.setBackground(Color.YELLOW);
		} else if (uiFile.getHeaderName().contains(".sek")) {
			fetchPanel.setBackground(Color.GREEN);
		} else if (uiFile.getHeaderName().contains(".ind")) {
			fetchPanel.setBackground(Color.RED);
		}

		panTop.add(blockPanel);
		panTop.add(fetchPanel);

		add(panTop, BorderLayout.NORTH);
	}

	public UIAbstractFile getUiFile() {
		return uiFile;
	}

	public void updateBlockPerformed(UpdateBlockEvent e) {
		table.setModel(new TabelaModel(uiFile.getFields(), uiFile.getData()));
	}

	public void valueChanged(TreeSelectionEvent e) {
		Node node = (Node) e.getPath().getLastPathComponent();
		if (node.getChildCount() == 0) {

			NodeElement nodeElement = node.getData().get(0);
			int newFilePointer = nodeElement.getBlockAddress() * uiFile.getRECORD_SIZE();
			uiFile.setFILE_POINTER(newFilePointer);
			try {
				uiFile.fetchNextBlock();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1.equals("delete")) {
			getParent().remove(this);
		}

	}

}
