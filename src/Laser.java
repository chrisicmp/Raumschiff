import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Diese Klasse implementiert ein Laserobjekt.
 * 
 * @author Frederik Shull
 */

public class Laser {
	
	double x;
	double y;
	BufferedImage image;
	public boolean activated;
	
	
	
	/**
	 * Der Konstruktor erzeugt ein neues Laserobjekt.
	 * 
	 * @author Frederik Shull
	 * 
	 * @param spieler Das Spielerobjekt, welches den Laser abgefeuert hat
	 */
	
	public Laser(Spieler spieler) {
		
		this.x = spieler.x + spieler.image.getWidth();
		this.y = spieler.y + (int)(spieler.image.getHeight() / 2);
		
		try {
			
			this.image = ImageIO.read(new File("./src/images/laser.png"));
		}
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Diese Methode bewegt den Laser mit einer bestimmten Geschwindigkeit
	 * von links nach rechts.
	 * 
	 * @author Frederik Shull
	 * 
	 * @return Gibt true zurück, wenn sich das Laserobjekt ausserhalb des
	 * Bildschirms befindet.
	 */
	
	public boolean move() {
		
		if (this.x < Main.windowWidth + this.image.getWidth())
			
			x += Main.laserSpeed;
		
		else return true;
		return false;
	}
}
