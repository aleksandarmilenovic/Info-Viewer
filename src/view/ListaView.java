package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.AppWindow;
import model.ListaModel;
import model.UIFile;
import model.file.UIINDFile;
import model.file.UISEKFile;
import model.file.UISERFile;

public class ListaView extends JList implements ListSelectionListener, MouseListener {

	public ListaView() {

		super();
		addListSelectionListener(this);
		addMouseListener(this);
		addMouseListener(new MojMisaAdapter());
	}

	public void deleteFile() {

		ListaModel model = (ListaModel) this.getModel();
		int selectedIndex = getSelectedIndex();

		File file = new File(model.getElementAt(selectedIndex).toString());

		if (selectedIndex != -1) {
			file.delete();
			model.remove(selectedIndex);

		}
	}

	class MojMisaAdapter extends MouseAdapter {
		FileView fileView;

		/*
		 * public long folderSize(File directory) { long length = 0; for (File
		 * file : directory.listFiles()) {
		 * System.out.println(file.getName().toString() + "   " + length +
		 * "  byte"); if (file.isFile()) length += file.length(); else length +=
		 * folderSize(file); } if (length == 0) { System.out.println("End");
		 * return 0; } else System.out.println("End"); return length; }
		 */
		public void mouseClicked(MouseEvent arg0) {
			try {
				if (arg0.getClickCount() == 1) {
					Object o = getModel().getElementAt(getSelectedIndex());
					if (o instanceof UISERFile) {
						UISERFile uiSERfile = (UISERFile) o;
						String path = uiSERfile.getPath() + uiSERfile.getFileName();
						File file = new File(path);
						AppWindow.getInstance().getBtoolbar().gettField1().setText(uiSERfile.getFileName());
						AppWindow.getInstance().getBtoolbar().gettField2()
								.setText(uiSERfile.getPath() + uiSERfile.getFileName());
						AppWindow.getInstance().getBtoolbar().gettField3().setText(file.length() + " byte");
						AppWindow.getInstance().getBtoolbar().gettField4().setText(uiSERfile.getDate());
						AppWindow.getInstance().getBtoolbar().gettField5().setText(uiSERfile.getLastModifiedDate());
					} else if (o instanceof UISEKFile) {
						UISEKFile uisekFile = (UISEKFile) o;
						String path = uisekFile.getPath() + uisekFile.getFileName();
						File file = new File(path);
						AppWindow.getInstance().getBtoolbar().gettField1().setText(uisekFile.getFileName());
						AppWindow.getInstance().getBtoolbar().gettField2()
								.setText(uisekFile.getPath() + uisekFile.getFileName());
						AppWindow.getInstance().getBtoolbar().gettField3().setText(file.length() + " byte");
						AppWindow.getInstance().getBtoolbar().gettField4().setText(uisekFile.getDate());
						AppWindow.getInstance().getBtoolbar().gettField5().setText(uisekFile.getLastModifiedDate());
					} else if (o instanceof UIINDFile) {
						UIINDFile uiindFile = (UIINDFile) o;
						String path = uiindFile.getPath() + uiindFile.getFileName();
						File file = new File(path);
						AppWindow.getInstance().getBtoolbar().gettField1().setText(uiindFile.getFileName());
						AppWindow.getInstance().getBtoolbar().gettField2().setText(path);
						AppWindow.getInstance().getBtoolbar().gettField3().setText(file.length() + " byte");
						AppWindow.getInstance().getBtoolbar().gettField4().setText(uiindFile.getDate());
						AppWindow.getInstance().getBtoolbar().gettField5().setText(uiindFile.getLastModifiedDate());
					}

					UIFile uifile = (UIFile) o;
					String path = uifile.getPath();
					File file = new File(path);
					long filesize = file.length();
					// System.out.println(file.lastModified());
					// System.out.println(filesize);
					// String size = ()filesize;
					AppWindow.getInstance().getBtoolbar().gettField5().setText(uifile.getLastModifiedDate());
					AppWindow.getInstance().getBtoolbar().gettField4().setText(uifile.getDate());
					AppWindow.getInstance().getBtoolbar().gettField1().setText(uifile.getFileName());
					AppWindow.getInstance().getBtoolbar().gettField2().setText(uifile.getPath());
					if (file.isDirectory()) {
						//long size = folderSize(file);
						// JOptionPane.showMessageDialog(null,"Size is:"+ size+"
						// Bytes");
						AppWindow.getInstance().getBtoolbar().gettField3().setText(" ");
						// SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
						//size = 0;
					} else {
						AppWindow.getInstance().getBtoolbar().gettField3().setText(filesize + "  bytes");
						// SwingUtilities.updateComponentTreeUI(AppWindow.getInstance());
					}

				}
				if (arg0.getClickCount() == 2) {
					Object o = getModel().getElementAt(getSelectedIndex());
					if (o instanceof UISERFile) {
						UISERFile uiSERfile = (UISERFile) o;
						if (!uiSERfile.isDirectory()) {
							// FileView fileView=new FileView(uiSERfile);
							FileViewInd fileView = new FileViewInd(uiSERfile);
							AppWindow.getInstance().setFileViewInd(fileView);

						}
					} else if (o instanceof UIINDFile) {
						UIINDFile uisekFile = (UIINDFile) o;
						if (!uisekFile.isDirectory()) {
							FileViewInd fileViewSek = new FileViewInd(uisekFile);
							AppWindow.getInstance().setFileViewInd(fileViewSek);
						}
					} else if (o instanceof UISEKFile) {
						UISEKFile uisekFile = (UISEKFile) o;
						if (!uisekFile.isDirectory()) {
							FileViewInd fileViewSek = new FileViewInd(uisekFile);
							AppWindow.getInstance().setFileViewInd(fileViewSek);
						}
					} else if (o instanceof UIFile) {
						UIFile uifile = (UIFile) o;
						if (uifile.isDirectory()) {
							setModel(new ListaModel(uifile.getPath()));
						} else {

							uifile.readFile();
							fileView = new FileView(uifile);
							AppWindow.getInstance().setFileView(fileView);
						}
					}
				} else if (SwingUtilities.isRightMouseButton(arg0)) {
					AppWindow.getInstance().getRightclick().show(arg0.getComponent(), arg0.getX(), arg0.getY());
				}

			} catch (Exception e) {

			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
