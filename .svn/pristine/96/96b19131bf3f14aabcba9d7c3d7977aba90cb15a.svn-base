package clock.Actions;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import helper_classes.Utilities;
import language.Localisation;
import values.Icons;

public class ClockAction extends AbstractAction{

	public ClockAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(NAME, Localisation.getInstance().getBundle().getString("clock"));
		putValue(SHORT_DESCRIPTION, Localisation.getInstance().getBundle().getString("clock"));	
		putValue(SMALL_ICON, Icons.CLOCK_ICON);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 EventQueue.invokeLater(ClockAction::runIt);
		
	}
	private static void runIt() {
        final JFrame j = new JFrame();
        j.setTitle(Localisation.getInstance().getBundle().getString("clock"));
        j.setIconImage(Utilities.loadBufferedImage("clock_24", ".png"));
        final JClock clock = new JClock(new CoolPaint());

        j.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clock.stop();
                j.dispose();
            }
        });

        j.add(clock);
        j.setBounds(20, 20, 480, 500);
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        clock.start();
    }


}
