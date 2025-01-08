import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** 
 * Diese Klasse implementiert einen Asteroiden.
 * 
 * @author Christopher Peter
 * @author Teo Foerste
 */

public class Asteroid {
	
	/** X-Koordinate des Asteroiden */
	double x;
	
	/** Y-Koordinate des Asteroiden */
	double y;
	
	/** Die Groesse (Breite) des Asteroiden in Pixel */
	int size;
	
	/** Das Array, welches die Bilddateien zur Darstellung des Asteroiden enthaelt */
	BufferedImage[] images;
	
	/** Der Index der Bilddatei im Array {@link#images}, 
	 *  durch die der Asteroid aktuell dargestellt wird */
	int imgIndex;
	
	/** Die Geschwindigkeit des Asteroiden */
	double speed;
	
	/** Gibt an ob der Asteroid aktiv ist */
	boolean active;
	
	/** Die Anzahl der aktuellen Trefferpunkte des Asteroiden */
	int hp;
	
	/** Die Anzahl der maximalen Trefferpunkte des Asteroiden */
	int hpMax;
	
	/** Die Menge an Mineralien, die der Asteroid enthaelt */
	int minerals;
	
	/** Die Menge an Gold, die der Asteroid enthaelt */
	int gold;
	
	
	
	/** Der Konstruktor erzeugt ein neues Objekt mithilfe der Methode
	 * {@link#newAsteroid}.
	 * 
	 * @param active Gibt an, ob der Asteroid aktiv ist
	 */
	
	public Asteroid(boolean active) {
		
		newAsteroid(active);
	}
	
	
	
	/**
	 * Diese Methode weist einem Asteroidenobjekt neue Werte zu.
	 * Es gibt verschiedene Asteroidentypen, von denen beim
	 * Aufruf dieser Methode einer zufaellig ausgewaehlt wird.
	 * 
	 * @param active Gibt an, ob der zu erstellende Asteroid aktiv sein soll
	 * 		  (siehe {@link#active})
	 * 
	 * @author Teo Foerste
	 */
	
	public void newAsteroid(boolean active) {
		
		this.imgIndex = 0;
		
		Random rnd = new Random();
		
		try {
			
			switch(rnd.nextInt(13)) {
			
			case 0:  // kleiner Asteroid 1
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_01.png"))};
				
				break;
				
			case 1:  // kleiner Asteroid 2
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_02.png"))};
				
				break;
				
			case 2:  // kleiner Asteroid 3
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_03.png"))};
				
				break;
				
			case 3:  // kleiner Asteroid 4
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_04.png"))};
				
				break;
				
			case 4:  // kleiner Asteroid 5
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_05.png"))};
				
				break;
				
			case 5:  // kleiner Asteroid 6
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_06.png"))};
				
				break;
				
			case 6:  // kleiner Asteroid 7
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_07.png"))};
				
				break;
				
			case 7:  // kleiner Asteroid 8
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_08.png"))};
				
				break;
				
			case 8:  // kleiner Asteroid 9
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_09.png"))};
				
				break;
				
			case 9:  // kleiner Asteroid 10
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_small_10.png"))};
				
				break;
				
			case 10:  // grosser Asteroid 1
				
				this.hpMax = 1;
				this.minerals = 0;
				this.gold = 0;
				
				this.images = new BufferedImage[] {ImageIO.read(new File("./src/images/asteroid_big_01.png"))};
				
				break;
				
			case 11:  // mineralhaltiger Asteroid 1
				
				this.hpMax = 5;
				this.minerals = 10 * (1 + rnd.nextInt(10));  // 10 bis 100 Mineralien in Zehnerschritten
				this.gold = 0;
				
				this.images = new BufferedImage[] {
						
					ImageIO.read(new File("./src/images/asteroid_mineral_01_1.png")),
					ImageIO.read(new File("./src/images/asteroid_mineral_01_2.png"))
				};
				
				break;
				
			case 12:  // riesiger Asteroid
				
				this.hpMax = 10;
				this.minerals = 10 * (10 + rnd.nextInt(41));  // 100 bis 500 Gold in Zehnerschritten
				this.gold = 10 * (1 + rnd.nextInt(10));  // 10 bis 100 Gold in Zehnerschritten
				
				this.images = new BufferedImage[] {
					
					ImageIO.read(new File("./src/images/asteroid_boss_01_1.png")),
					ImageIO.read(new File("./src/images/asteroid_boss_01_2.png"))
				};
				
				break;
			}
		}
		
		catch(IOException e) {
			
			e.printStackTrace();
		}
		
		x = Main.windowWidth + (double) (this.images[imgIndex].getWidth()) + rnd.nextDouble() * Main.windowWidth;
		y = (double) this.images[this.imgIndex].getHeight() + rnd.nextDouble() * ((double) Main.windowHeight - 2.0 * (double) this.images[this.imgIndex].getHeight());
		
		this.active = active;
		this.size = this.images[0].getWidth();
		this.hp = this.hpMax;
		
		this.speed = Main.difficulty * rnd.nextDouble() * (64.0 / (double) this.size);
		
	}
	
	
	
	/** 
	 * Diese Methode bewegt einen Asteroiden von rechts nach links.
	 * 
	 * @author Christopher Peter 
	 * @author Teo Foerste
	 * */
	
	public void move() {
		
		x -= this.speed;
	}
	
	
	
	/**
	 * Diese Methode aktualisiert die Bilddatei abhängig vom Schaden, den der Asteroid nimmt.
	 * Die Berechnung wird nur durchgefuehrt, wenn mehr als ein Bild im Array {@link#images}
	 * vorhanden ist, aber nicht mehr als die {@link#hpMax Anzahl der maximalen Trefferpunkte}, 
	 * da sie sonst nicht korrekt funktioniert.
	 * 
	 * @author Teo Foerste
	 */
	
	public void updateImg() {
		
		if (this.images.length > 1 && this.images.length <= this.hpMax) {
			
			this.imgIndex = (this.images.length - 1) - (int) Math.round(((double) this.hp / this.hpMax)) * (1 / (this.images.length - 1));
		}
	}
	
	
	
	/**
	 * Diese Methode aktiviert den Asteroiden, sodass er bewegt wird.
	 * 
	 * @author Dominik Duda
	 */
	
	public void setActive() {
		
		this.active = true;
	}
	
	
	
	/**
	 * Diese Methode setzt den Asteroiden auf die angegebe Position
	 * 
	 * @author Dominik Duda
	 * 
	 * @param posx X-Position des Asteroiden
	 * @param posy Y-Position des Asteroiden
	 */
	
	public void setPosition(double posx, double posy) {
		
		this.x = posx;
		this.y = posy;
	}
}
