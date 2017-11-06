package helper_classes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Utilities {
	
	public static BufferedImage loadBufferedImage(String name, String extension) {
		try {
			if (!name.startsWith("/images/")) {
				name = "/images/" + name + extension;
			}
			return ImageIO.read(Utilities.class.getResource(name));
		} catch (IOException e) {
			System.err.println("Image not found!");
			e.printStackTrace();
		}
		return null;
	}
	public static ImageIcon loadImageIcon(String imageName, String extension) {
		String imgLocation = "/images/" + imageName + extension;
		java.net.URL imageURL = Utilities.class.getResource(imgLocation);

		if (imageURL == null) {
			System.err.println("Resource not found: " + imgLocation);
			return null;
		} else {
			return new ImageIcon(imageURL);
		}
	}
	
	
}
