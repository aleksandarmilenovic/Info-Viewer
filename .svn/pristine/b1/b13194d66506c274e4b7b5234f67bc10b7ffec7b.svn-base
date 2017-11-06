package view;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SQLDialog extends JDialog {

	private JButton insert;
	private JButton update;
	private JButton delete;
	private JButton select;

	public SQLDialog() {
		getContentPane().setLayout(new GridLayout(2, 2));
		initDialog();
		setTitle("SQL Help");
		setSize(300, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void initDialog() {
		getContentPane().add(insert());
		getContentPane().add(update());
		getContentPane().add(delete());
		getContentPane().add(select());
	}

	public JButton insert() {
		insert = new JButton("INSERT");
		insert.setBackground(SystemColor.inactiveCaption);
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"INSERT   INTO STUDENTI(STRUKA, BROJ_INDEKSA, PREZIME, \nIME_RODITELJA, IME, POL, JMBG, DATUM_RODJENJA,ADRESA, \nTELEFON) ",
						"INSERT", JOptionPane.CLOSED_OPTION);
			}
		});
		return insert;
	}

	public JButton update() {
		update = new JButton("UPDATE");
		update.setBackground(SystemColor.inactiveCaption);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"UPDATE STUDENTI \nSET TELEFON='021/6632-928', ADRESA='Vojvode Stepe 1A' \nWHERE STRUKA='E' AND BROJ_INDEKSA=9293 ",
						"UPDATE", JOptionPane.CLOSED_OPTION);
			}
		});
		return update;
	}

	public JButton delete() {
		delete = new JButton("DELETE");
		delete.setBackground(SystemColor.inactiveCaption);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "DELETE FROM STUDENTI \nWHERE STRUKA='E' AND BROJ_INDEKSA=9293",
						"DELETE", JOptionPane.CLOSED_OPTION);
			}
		});
		return delete;
	}

	public JButton select() {
		select = new JButton("SELECT");
		select.setBackground(SystemColor.inactiveCaption);
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"SELECT   STRUKA,BROJ_INDEKSA,PREZIME,IME_RODITELJA, \nIME,POL,JMBG,DATUM_RODJENJA,ADRESA,TELEFON \nFROM STUDENTI ",
						"SELECT", JOptionPane.CLOSED_OPTION);
			}
		});
		return select;
	}

}
