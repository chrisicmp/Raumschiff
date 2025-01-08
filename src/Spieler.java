import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** 
 * Diese Klasse implementiert den Spieler.
 * 
 * @author Christopher Peter
 */

public class Spieler {
	
	/** X-Koordinate des Spielers */
	double x;
	
	/** Y-Koordinate des Spielers */
	double y;
	
	/** Die Geschwindigkeit des Spielers */
	double speed;
	
	/** Die Anzahl der Leben des Spielers */
	int lifes;
	
	/** Der Schaden, den der Spieler mit einem Laserschuss generiert */
	int damage;
	
	/** Der Status des Spiels */
	boolean gameOver;
	
	/** Die Bilddatei des Spielerobjekts*/
	BufferedImage image;
	
	/** Die Bilddatei des Antriebs */
	BufferedImage boosterImage;
	
	/** Der Status des Antriebs */
	int boostState;
	
	/** Die Geschwindigkeit der Antriebsanimation */
	int boostSpeed;
	
	/** Wurde bereits ein Laser abgefeuert? */
	boolean laserShot;
	
	/** Der Cooldown des Lasers in Nanosekunden */
	int cooldownLimit;
	
	/** Die Zählvariable für den Cooldown */
	int cooldown;
	
	
	
	/** Der Konstruktor erzeugt ein neues Spielerobjekt.
	 * 
	 * @param x X-Koordinate der Startposition
	 * @param y Y-Koordinate der Startposition
	 * @param speed Geschwindigkeit des Raumschiffs
	 *  
	 * @throws IOException Fehler beim Importieren der Bilddatei
	 */
	
	public Spieler(double x, double y, double speed) throws IOException {
		
		if(x >= 0 && y >= 0 && speed >= 0){
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.lifes = 3;
			this.damage = 1;
			this.gameOver = false;
			this.image = ImageIO.read(new File("./src/images/player_01.png"));
			this.boostState = 0;
			this.boostSpeed = 50;
			cooldownLimit = 50;
			cooldown = 0;
			this.laserShot = false;
			this.genBoost();
		} else{
			this.x = 16;	//Spieler wird auf 
			this.y = Main.windowHeight / 2 - 64; 
		}
		 
	}
	
	
	
	/** Diese Methode bewegt den Spieler in die angegebene Richtung.
	 * 
	 * @author Dominik Duda
	 * 
	 * @param direction Die Richtung, in die sich der Spieler bewegen soll <br>
	 *  -1 : Keine Bewegung <br> 0 : Nach oben <br> 1 : Nach oben rechts <br>
	 *  2 : Nach rechts <br> 3 : Nach unten rechts <br> 4 : Nach unten <br>
	 *  5 : Nach unten links <br> 6 : Nach links <br> 7 : Nach oben links
	 * 
	 */
	
	public void movePlayer(int direction) {
		
		switch(direction) {
			
			case 0:
				this.MoveUp();
				break;
			case 1:
				this.MoveUp();
				this.MoveRight(Main.windowWidth-70);
				break;
			case 2:
				this.MoveRight(Main.windowWidth-70);
				break;
			case 3:
				this.MoveRight(Main.windowWidth-70);
				this.MoveDown(Main.windowHeight-85);
				break;
			case 4:
				this.MoveDown(Main.windowHeight-85);
				break;
			case 5:
				this.MoveDown(Main.windowHeight-85);
				this.MoveLeft();
				break;
			case 6:
				this.MoveLeft();
				break;
			case 7:
				this.MoveLeft();
				this.MoveUp();
				break;
			default:
				if (boostState > 0)
					boostState--;
				this.genBoost();
				break;
		}
	}
	
	
	
	/** 
	 *  Diese Methode bewegt den Spieler nach oben.
	 *  
	 *  @author Dominik Duda
	 */
	
	private void MoveUp() {
		if(y - speed >= 0) {
			y -= speed;
		} else {
			y = 0;
		}
	}
	
	
	
	/** 
	 *  Diese Methode bewegt den Spieler nach unten.
	 *   
	 *  @author Dominik Duda
	 */
	
	private void MoveDown(int screenheight) {
		if(y + speed <= screenheight) {
			y += speed;
		} else {
			y = screenheight;
		}
	}
	
	
	
	/** 
	 *  Diese Methode bewegt den Spieler nach links.
	 *  
	 *  @author Dominik Duda
	 */
	
	private void MoveLeft() {
		if(x - speed >= 0) {
			x -= speed;
		} else {
			x = 0;
		}
		if (boostState > 0)
			boostState--;
		this.genBoost();
	}
	
	
	
	/** 
	 * Diese Methode bewegt den Spieler nach rechts.
	 * 
	 * @author Dominik Duda
	 */
	
	private void MoveRight(int screenwidth) {
		if(x + speed <= screenwidth / 2) {
			x += speed;
			if (boostState < boostSpeed * 7)
				boostState++;
			else
				boostState = boostSpeed * 4;
		} else {
			x = screenwidth / 2;
			if (boostState > 0)
				boostState--;
		}
		this.genBoost();
	}
	
	private void genBoost() {
		try {
			this.boosterImage = ImageIO.read(new File("./src/images/booster_0" + (boostState) / boostSpeed + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
