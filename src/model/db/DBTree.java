package model.db;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import gui.AppWindow;
import view.FileView;
import view.FileViewDATABASE;

@SuppressWarnings("serial")
public class DBTree extends JTree implements MouseListener {

	public DBTree() {
		super(new DefaultTreeModel(new DBNode("(no database)", DBNode.DATABASE, "", 0, 0, false)));
		addMouseListener(this);
		setCellRenderer(new DBTreeCellRendered());
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
			DBNode dbNode = (DBNode) this.getLastSelectedPathComponent();
			if (dbNode.getType() == DBNode.DATABASE_TABLE) {
				UIDBFile uidbfile = new UIDBFile(dbNode.getName());
				FileViewDATABASE fileViewDB = new FileViewDATABASE(uidbfile);
				AppWindow.getInstance().setFileViewDB(fileViewDB);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
