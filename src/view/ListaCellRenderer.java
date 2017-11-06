package view;

import java.awt.Component;
import java.net.URL;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;

import model.UIFile;
import model.file.UIINDFile;
import model.file.UISEKFile;
import model.file.UISERFile;

public class ListaCellRenderer extends DefaultListCellRenderer {

	public ListaCellRenderer() {

		super();
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof UISERFile) {
			if (!((UISERFile) value).isDirectory()) {
				URL imageURL = getClass().getResource("foldericon/file_icon_yellow_24.png");
				Icon icon = null;
				if (imageURL != null)
					icon = new ImageIcon(imageURL);
				setIcon(icon);
			}
		} else if (value instanceof UISEKFile) {
			if (!((UISEKFile) value).isDirectory()) {
				URL imageURL = getClass().getResource("foldericon/file_icon_green_24.png");
				Icon icon = null;
				if (imageURL != null)
					icon = new ImageIcon(imageURL);
				setIcon(icon);
			}
		}

		else if (value instanceof UIINDFile) {
			if (!((UIINDFile) value).isDirectory()) {
				URL imageURL = getClass().getResource("foldericon/file_icon_red_24.png");
				Icon icon = null;
				if (imageURL != null)
					icon = new ImageIcon(imageURL);
				setIcon(icon);
			}
		}

		if (value instanceof UIFile) {

			if (((UIFile) value).isDirectory()) {
				URL imageURL = getClass().getResource("foldericon/folder_24.png");
				Icon icon = null;

				if (imageURL != null)
					icon = new ImageIcon(imageURL);

				setIcon(icon);
			} else {

				URL imageURL = getClass().getResource("foldericon/file_icon_24.png");
				Icon icon = null;
				if (imageURL != null)
					icon = new ImageIcon(imageURL);
				setIcon(icon);
			}
		}
		return this;

	}
}
