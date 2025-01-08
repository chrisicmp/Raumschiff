/**
 * Diese Klasse implementiert einen Stern.
 * <br><br>
 * Ein Stern ist ein weisser Punkt, der sich im Hintergrund des Spiels von
 * rechts nach links bewegt und dabei verblasst. Dies geschieht unterschiedlich
 * schnell. Sterne haben keinen Einfluss auf das Spielgeschehen.
 * 
 * @author Teo Foerste
 */

public class Star {
	
	/** X-Koordinate des Sterns **/
	int x;
	
	/** Y-Koordinate des Sterns **/
	int y;
	
	/** Der Durchmesser des Sterns in Pixel **/
	int size;
	
	/** Der Alpha-Wert des Sterns **/
	int alpha;
	
	/** Die Geschwindigkeit des Sterns in Pixel pro Takt **/
	double speed;
	
	/** Der Wert, um den der Alphawert des Sterns bei jedem Takt verringert wird
	 * (entspricht der Geschwindigkeit des Verblassens) **/
	double fade;
	
	
	
	/**
	 * Der Konstruktor erzeugt ein Sternobjekt an einer zufaelligen Position
	 * und mit zufaelliger Transparenz.
	 */
	
	public Star() {
		
		x = (int) ((double) Main.windowWidth * Math.random());
		y = (int) ((double) Main.windowHeight * Math.random());
		size =  (int) (4.0 *(Math.random()));
		alpha = (int) (Math.random() * 255);
		speed = (int) Math.random() * 1.5;
		fade = Math.random();
	}
	
	
	
	/**
	 * Diese Methode beschreibt die Bewegung sowie das Verblassen der Sterne.
	 */
	
	public void move() {
		
		if (alpha >= fade) {
			
			x -= speed;
			alpha -= fade;
		}
		
		else {
			
			x = (int) ((double) Main.windowWidth * Math.random());
			y = (int) ((double) Main.windowHeight * Math.random());
			size =  (int) (4.0 *(Math.random()));
			alpha = (int) (Math.random() * 255);
			speed = Math.random() * 1.5;
			fade = Math.random();
		}
	}
}