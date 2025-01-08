import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/** 
 * Diese Klasse implementiert einen Meteor
 * @author chris
 *
 */
public class Meteor {
	
	/** x Koordinate des Meteors */
	int x;
	
	/** y Koordinate des Meteors */
	int y;
	
	/** Bild/Aussehen des Meteors*/
	BufferedImage imgSpieler;
	
	/** Konstruktor erzeugt einen Meteor */
	public Meteor(int x, int y) {
		this.x = x;
		this.y = y;
		//this.imgMeteor = ImageIO.read(new File("HIER NAME DER JPG"));
	}
	
	
}

