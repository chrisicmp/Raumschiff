import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * Diese Klasse implementiert einen MouseListener.
 * 
 * @author Dominik Duda
 */

public class MouseHandler extends JFrame implements MouseListener {
	
	/**
	 * Diese Methode wird aufgerufen, wenn eine Maustaste gedrueckt wurde.
	 * 
	 * @param e Ein MouseEvent, welches Informationen ueber gedrueckte
	 * Maustasten liefert.
	 */
	
	public void mouseClicked(MouseEvent e) {
		if(!Main.spieler.gameOver && !Main.gamemode) {
			Asteroid asteroid = null;
			for(Asteroid asteroids : Main.AsteroidList) {
				if(!asteroids.active) {
					asteroid = asteroids;
					break;
				}
			}
			if(asteroid != null) {
				if(e.getX() <= Main.windowWidth / 2) return;
				asteroid.setPosition(e.getX(), e.getY());
				asteroid.setActive();
			}
		}
	}

	public void mousePressed(MouseEvent e) {} //ungenutzt

	public void mouseReleased(MouseEvent e) {} //ungenutzt

	public void mouseEntered(MouseEvent e) {} //ungenutzt

	public void mouseExited(MouseEvent e) {} //ungenutzt
	
	
		
	
}
