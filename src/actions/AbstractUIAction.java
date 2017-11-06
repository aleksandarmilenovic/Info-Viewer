package actions;

import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 
 * Ukoliko nasledimo klasu AbstractAction mi kreiramo Listener-a 
 * kojeg možemo priključiti komponentama. 
 * Klasa je apstraktna jer ne implementira metodu actionPerformed()
 * Ovu klasu nasleđuju sve akcije sa menija i toolbara 
 * 
 */



public  abstract class AbstractUIAction extends AbstractAction{
    /**
     * Kreira ikonu na osnovu zadatog imena
     * @param fileName
     * @return
     */
	public Icon loadIcon(String fileName){
		URL imageURL = getClass().getResource(fileName);
		Icon icon = null;
		
		if (imageURL != null) {                      
	        icon = new ImageIcon(imageURL);
	    } else {                                     
	        //System.err.println("Resource not found: " + fileName);
	    }

		return icon;
	}

	
	


}
