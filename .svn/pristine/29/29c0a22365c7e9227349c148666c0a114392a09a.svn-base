package model.db;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import values.Icons;

@SuppressWarnings("serial")
public class DBTreeCellRendered extends DefaultTreeCellRenderer {

	public DBTreeCellRendered() {
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		DBNode node = (DBNode) value;
		if (node.getType() == DBNode.DATABASE_COLUMN) {
			if (node.isPRIMARY_KEY() && !node.isSECONDARY_KEY()) {

				URL imageURL = getClass().getResource("images/key_24.png");
				Icon icon = null;
				if (imageURL != null)
					icon = new ImageIcon(imageURL);
				setIcon(icon);
			} else if (node.isSECONDARY_KEY()) {
				setIcon(Icons.KEYGRAY);
			} else {
				setIcon(Icons.COLUMN);
			}
		} else if (node.getType() == DBNode.DATABASE) {
			URL imageURL = getClass().getResource("images/db.png");
			Icon icon = null;
			if (imageURL != null)
				icon = new ImageIcon(imageURL);
			setIcon(icon);

		} else if (node.getType() == DBNode.DATABASE_TABLE) {
			setIcon(Icons.TABLE);
		} else if (node.getType() == DBNode.DATABASE_FOLDER) {
			setIcon(Icons.DBFOLDER);
		}
		return this;

	}
}
