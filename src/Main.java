import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame {
	
	/** Das Spielerobjekt */
	static Spieler spieler;
	
	/** Asteroidenobjekt für die Methoden:
	 * {@link #doCollide(ArrayList, ArrayList)} und 
	 * {@link #doCollide(Spieler, ArrayList)}*/
	public static Asteroid asteroidCollide;
	
	/** Eine Liste aller Sternobjekte */
	static ArrayList<Star> starfield;
	
	/** Eine Liste aller Asteroidenobjekte */
	static ArrayList<Asteroid> AsteroidList;
	
	/** Eine Liste aller Laserobjekte */
	static ArrayList<Laser> LaserList;
	
	/** Eine Liste aller Laserobjekte, die entfernt werden sollen */
	static ArrayList<Laser> ClearLaserList;
	
	/** Das Spielfenster */
	static JFrame mainFrame;
	
	/** Das Grafikobjekt für das Spielpanel */
	static Graphics graphics;
	
	/** Die Breite des Spielfensters */
	static int windowWidth = 1280;
	
	/** Die Hoehe des Spielfensters */
	static int windowHeight = 640;
	
	/** Die Anzahl der Sterne im Hintergrund */
	static int stars = 100;
	
	/** Die Menge an Mineralien, die dem Spieler zur Verfuegung stehen */
	static int minerals = 0;
	
	/** Die Menge an Gold, die dem Spieler zur Verfuegung steht */
	static int gold = 0;
	
	/** Der Schwierigkeitsgrad (beeinflusst die Geschwindigkeit) */
	static double difficulty;
	
	/** Das Bild für die Lebensanzeige */
	static BufferedImage heart;
	
	/** Das Bild für die Mineralienanzeige */
	static BufferedImage mineralsImg;
	
	/** Das Bild für die Goldanzeige */
	static BufferedImage goldImg;
	
	/** Die Geschwindigkeit des Lasers */
	static double laserSpeed = 2.0;
	
	/** Der aktuelle Spielmodus: true = Singlelayer, false = Multiplayer */
	static boolean gamemode = true;

	
	
	public static void main(String[] args) throws IOException {
		
		Random rnd = new Random();
		
		difficulty = 0.5;
		
		spieler = new Spieler(16, windowHeight / 2 - 64, 0.5d);
		
		heart = ImageIO.read(new File("./src/images/heart32.png"));
		mineralsImg = ImageIO.read(new File("./src/images/minerals.png"));
		goldImg = ImageIO.read(new File("./src/images/gold.png"));

		
		// Erstellen der Asteroiden
		AsteroidList = new ArrayList<Asteroid>();
		
		for (int x = 0; x < 10; x++) {
			
			if (gamemode) {
				
				AsteroidList.add(new Asteroid(true));
			}
			
			else {
				
				AsteroidList.add(new Asteroid(false));
			}
		}
		
		// Erstellen der Sterne
		starfield = new ArrayList<Star>();	
		
		for (int i = 0; i < stars; i++) {
			
			starfield.add(new Star());
		}
		
		// Erstellen der Laserliste
		LaserList = new ArrayList<Laser>();
		
		// Erstellen der Liste fuer die zu loeschenden Laser.
		ClearLaserList = new ArrayList<Laser>();
		
		// Erzeugen des Spielfensters
		createMainFrame();
		
		// Erzeugen des Spielpanels
		JPanel spielPanel = new JPanel() {
			
			protected void paintComponent(Graphics graphics) {
				
		        super.paintComponent(graphics);
		        
		        for (Star star : starfield) {
		        	
		        	Color color = new Color (255, 255, 255, star.alpha);
		        	graphics.setColor(color);
					graphics.fillArc(star.x, star.y, star.size, star.size, 0, 360);
				}
		        
				graphics.drawImage(spieler.image, (int)spieler.x, (int)spieler.y, null);
				graphics.drawImage(spieler.boosterImage, (int)spieler.x - 64, (int)spieler.y - 8, null);
				
				for (Asteroid asteroid : AsteroidList) {
					
					graphics.drawImage(asteroid.images[asteroid.imgIndex], (int)asteroid.x, (int)asteroid.y, null);
				}
				
				for (Laser laser : LaserList) {
					
					if (laser != null)
						
						graphics.drawImage(laser.image, (int)laser.x, (int)laser.y, null);
				}
				
				for (int i = 0; i < spieler.lifes; i++) {
					
					graphics.drawImage(heart, 8 + i * 32, 8, null);
				}
				
				graphics.setColor(Color.WHITE);
			
				graphics.drawImage(mineralsImg, 32 + spieler.lifes * 32, 8, null);
				graphics.drawImage(goldImg, 32 + spieler.lifes * 32 + 100, 8, null);
				
				graphics.drawString("" + minerals, 32 + spieler.lifes * 32 + 40, 28);
				graphics.drawString("" + gold, 32 + spieler.lifes * 32 + 140, 28);
			}
		};
		
		spielPanel.setBackground(Color.BLACK);
		mainFrame.add(spielPanel);
        
        while (!spieler.gameOver) {
        	
        	// Position der Sterne berechnen
        	for (int i = 0; i < starfield.size(); i++) {
        		
        		starfield.get(i).move();
        	}
        	
        	// Position der Asteroiden berechnen
        	for (Asteroid asteroid : AsteroidList) {
        		
        		if (asteroid.active) {
        			
        			if (asteroid.x < 0 - asteroid.size) {
        				
        				asteroid.newAsteroid(true);
        			}
        			
        			else {
        				
        				asteroid.move();
        			}
        		}
        	}
        	
        	// Laser abfeuern
        	if (KeyHandler.getSpacebar()) {
        		
        		if (spieler.laserShot == false && spieler.cooldown <= 0) {
        			
            		LaserList.add(new Laser(spieler));
            		spieler.laserShot = true;
            		spieler.cooldown = spieler.cooldownLimit;
        		}
        		
        		else {
        			
        			spieler.cooldown--;
        		}
        	}
        	
        	else {
        		
    			spieler.laserShot = false;
        	}
        	
        	// Laser bewegen
			for (Laser laser : LaserList) {
				
				if (laser != null) {
					
					if (laser.move())
						
						ClearLaserList.add(laser);
				}
			}
			
			for (Laser klaser : ClearLaserList)
				LaserList.remove(klaser);
			

			// Spieler bewegen
        	spieler.movePlayer(KeyHandler.getDirection());
        	
        	// Kollision des Lasers mit einem Asteroiden
        	asteroidCollide = doCollide(LaserList, AsteroidList);
        	
        	if (asteroidCollide != null) {
        		
        		if (asteroidCollide.hp - spieler.damage <= 0) {
        			
	        		minerals += asteroidCollide.minerals;
					gold += asteroidCollide.gold;
					
					asteroidCollide.newAsteroid(true);
        		}
        		
        		else {
        			
        			asteroidCollide.hp -= spieler.damage;
        			asteroidCollide.updateImg();
        		}
        	}
        	
        	// Kollision des Spielers mit einem Asteroiden
        	asteroidCollide = doCollide(spieler, AsteroidList);
        	
        	if (asteroidCollide != null) {
        		
        		spieler.x = 0;
        		spieler.y = windowHeight / 2;
        		asteroidCollide.x = windowWidth + 50;
        		spieler.lifes -= 1;
        		
        		if (spieler.lifes == 0) {
        			
        			spieler.gameOver = true;
        		}
        	}
        	
        	// zeichnen
        	mainFrame.repaint();
        	
        	// Pause
        	try {
        		
				TimeUnit.NANOSECONDS.sleep(1);
			}
        	
        	catch (InterruptedException e) {
        		
				e.printStackTrace();
			}
        } 
	}
	
	
	/**
	 * Diese Methode erzeugt das Spielfenster.
	 * 
	 * @author Teo Foerste
	 */
	
	public static void createMainFrame() {
		
		mainFrame = new JFrame();
		
		mainFrame.setVisible(true);
		mainFrame.setMinimumSize(new Dimension(windowWidth, windowHeight));
		mainFrame.setPreferredSize(new Dimension(windowWidth, windowHeight));
		mainFrame.setTitle("Asteroid Field");
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		
		mainFrame.pack();
		
		mainFrame.addKeyListener(new KeyHandler());
		mainFrame.addMouseListener(new MouseHandler());
	}
	
	
	
	/**
	 * Diese Methode ueberprueft, ob ein Asteroid mit dem Spieler kollidiert.
	 * 
	 * @author Frederik Shull
	 * 
	 * @param spieler Das zu ueberpruefende Spielerobjekt
	 * @param asteroidList Eine Liste der zu ueberpruefenden Asteroidenobjekte
	 * 
	 * @return Gibt im Fall einer Kollision das betroffene Asteroidenobjekt zurueck.
	 * 		   Findet keine Kollision statt, wird null zurückgegeben.
	 */
	
	public static Asteroid doCollide(Spieler spieler, ArrayList<Asteroid> asteroidList) {
		
		float spielerWidth = spieler.image.getWidth();
		float spielerHeight = spieler.image.getHeight();
		
		for (Asteroid asteroid : asteroidList) {
			
			float asteroidWidth = asteroid.images[asteroid.imgIndex].getWidth();
			float asteroidHeight = asteroid.images[asteroid.imgIndex].getHeight();
			
			if (
				(spieler.x + spielerWidth > asteroid.x) &&
				(spieler.x < asteroid.x + asteroidWidth) &&
				(spieler.y + spielerHeight > asteroid.y) &&
				(spieler.y < asteroid.y + asteroidHeight)) {
				
				return asteroid;
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * Diese Methode ueberprueft, ob ein Asteroid vom Laser getroffen wird.
	 * Im Fall einer Kollision wird das betroffene Laserobjekt der
	 * {@link #ClearLaserList} hinzugefuegt.
	 * 
	 * @author Christopher Peter
	 * @author Teo Foerste
	 * 
	 * @param laserList Eine Liste der zu ueberpruefenden Laserobjekte
	 * @param asteroidList Eine Liste der zu ueberpruefenden Asteroidenobjekte
	 * 
	 * @return Gibt im Fall einer Kollision das betroffene Asteroidenobjekt zurueck.
	 * 		   Findet keine Kollision statt, wird null zurückgegeben.
	 */
	
	public static Asteroid doCollide(ArrayList<Laser> laserList, ArrayList<Asteroid> asteroidList) {
		
		for (Asteroid asteroid : asteroidList) {
			
			float asteroidWidth = asteroid.images[asteroid.imgIndex].getWidth();
			float asteroidHeight = asteroid.images[asteroid.imgIndex].getHeight();
			
			for (Laser laser : laserList) {
				
				float laserWidth = laser.image.getWidth();
				float laserHeight = laser.image.getHeight();
				
				if (
						(laser.x + laserWidth > asteroid.x) &&
						(laser.x < asteroid.x + asteroidWidth) &&
						(laser.y + laserHeight > asteroid.y) &&
						(laser.y < asteroid.y + asteroidHeight)
				) {
						
					ClearLaserList.add(laser);
					return asteroid;
				}
			}	
		}
		
		return null;
	}
	
	
	
	/**
	 * Diese Methode ueberprueft, ob eine Kollision zweier Sprites fuer
	 * den Spieler erkennbar ist oder lediglich transparente Pixel
	 * miteinander kollidieren.
	 * 
	 * @author Teo Foerste
	 * 
	 * @param img1 Bilddatei des ersten Sprites
	 * @param img1x X-Koordinate des oberen linken Pixels des ersten Sprites auf dem Spielfeld
	 * @param img1y Y-Koordinate des oberen linken Pixels des ersten Sprites auf dem Spielfeld
	 * @param img2 Bilddatei des zweiten Sprites
	 * @param img2x X-Koordinate des oberen linken Pixels des zweiten Sprites auf dem Spielfeld
	 * @param img2y Y-Koordinate des oberen linken Pixels des zweiten Sprites auf dem Spielfeld
	 * 
	 * @return Gibt true zurueck, wenn an mindestens einer Position zwei nicht
	 * vollstaendig transparente Pixel miteinander kollidieren.
	 */
	
	public static boolean trueCollision(
			
			BufferedImage img1, int img1x, int img1y,
			BufferedImage img2, int img2x, int img2y) {
		
		int x1;
		int x2;
		int y1;
		int y2;
		
		int w;
		int h;
		
		int absX = Math.abs(img1x - img2x);
		int absY = Math.abs(img1y - img2y);
		
		if (img1x >= img2x) {
			
			w = Math.abs(img2.getWidth()-absX);
			
			x1 = 0;
			x2 = img2.getWidth() - w;
		}
		
		else {
			
			w = Math.abs(img1.getWidth()-absX);
			
			x1 = img1.getWidth() - w;
			x2 = 0;
		}
		
		if (img1y >= img2y) {
			
			h = Math.abs(img2.getHeight()-absY);
			
			y1 = 0;
			y2 = img2.getHeight() - h;
		}
		
		else {
			
			h = Math.abs(img1.getHeight()-absY);
			
			y1 = img1.getHeight() - h;
			y2 = 0;
		}
		
		for (int i = 0; i < h; i++) {
			
			for (int j = w-1; j >= 0; j--) {
				
				if (img1.getRGB(x1 + j, y1 + i) >> 24 != 0) {
				 
					if (img2.getRGB(x2 + j, y2 + i) >> 24 != 0) {
					
						return true;
					}
				}
			}
		}
		
		return false;
	}
}