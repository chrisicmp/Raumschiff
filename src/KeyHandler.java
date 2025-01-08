import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Diese Klasse implementiert einen KeyListener.
 * 
 * @author Dominik Duda
 */

public class KeyHandler extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	/** Gibt an, ob eine "nach oben" Taste (W bzw. Pfeil nach oben) gedrueckt wurde */
	protected static boolean up_pressed = false;
	
	/** Gibt an, ob eine "nach unten" Taste (S bzw. Pfeil nach unten) gedrueckt wurde */
	protected static boolean down_pressed = false;
	
	/** Gibt an, ob eine "nach links" Taste (A bzw. Pfeil nach links) gedrueckt wurde */
	protected static boolean left_pressed = false;
	
	/** Gibt an, ob eine "nach rechts" (D bzw. Pfeil nach rechts) gedrueckt wurde */
	protected static boolean right_pressed = false;
	
	/** Gibt an, ob die Leertaste gedruckt wurde */
	protected static boolean spacebar = false;
	
	public void keyTyped(KeyEvent e) {} //ungenutzt
	
	

	/**
	 * Diese Methode wird aufgerufen, wenn eine Taste gedrueckt wurde. Sie
	 * setzt den jeweiligen boolean ({@link#up_pressed}, {@link#down_pressed}
	 * etc.) auf true.
	 * 
	 * @author Dominik Duda
	 * 
	 * @param e Ein KeyEvent, welches Informationen über gedrueckte Tasten liefert
	 */
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == 87 || e.getKeyCode() == 38 && up_pressed == false) {
			up_pressed = true;
		}
		
		if(e.getKeyCode() == 83 || e.getKeyCode() == 40 && down_pressed == false) {
			down_pressed = true;
		}
		
		if(e.getKeyCode() == 65 || e.getKeyCode() == 37 && left_pressed == false) {
			left_pressed = true;
		}
		
		if(e.getKeyCode() == 68 || e.getKeyCode() == 39 && right_pressed == false) {
			right_pressed = true;
		}
		
		if (e.getKeyCode() == 32 && spacebar == false)
			spacebar = true;
	}
	
	

	/**
	 * Diese Methode wird aufgerufen, wenn eine Taste losgelassen wurde. Sie
	 * setzt den jeweiligen boolean ({@link#up_pressed}, {@link#down_pressed}
	 * etc.) auf true.
	 * 
	 * @author Dominik Duda
	 * 
	 * @param e Ein KeyEvent, welches Informationen über gedrueckte Tasten liefert
	 */
	
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == 87 || e.getKeyCode() == 38  && up_pressed == true) {
			up_pressed = false;
		}
		
		if(e.getKeyCode() == 83 || e.getKeyCode() == 40 && down_pressed == true) {
			down_pressed = false;
		}
		
		if(e.getKeyCode() == 65 || e.getKeyCode() == 37 && left_pressed == true) {
			left_pressed = false;
		}
		
		if(e.getKeyCode() == 68 || e.getKeyCode() == 39 && right_pressed == true) {
			right_pressed = false;
		}
		
		if (e.getKeyCode() == 32 && spacebar == true)
			spacebar = false;
	}
	
	
	
	/** 
	 * Diese Methode wertet die gedrueckten Tasten aus.
	 * 
	 * @author Dominik Duda
	 * 
	 * @return Gibt die Richtung zurueck, in die sich der Spieler bewegen soll <br><br>
	 *  -1 : keine Richtung<br>
	 *   0 : oben<br>
	 *   1 : oben rechts<br>
	 *   2 : rechts<br>
	 *   3 : unten rechts<br>
	 *   4 : unten<br>
	 *   5 : unten links<br>
	 *   6 : links<br>
	 *   7 : oben links
	 *  
	 */
	
	public static int getDirection() {
		if(up_pressed && !down_pressed && !left_pressed && !right_pressed) {
			return 0;
		} else if (up_pressed && !down_pressed && !left_pressed && right_pressed) {
			return 1;
		} else if (!up_pressed && !down_pressed && !left_pressed && right_pressed) {
			return 2;
		} else if (!up_pressed && down_pressed && !left_pressed && right_pressed) {
			return 3;
		} else if (!up_pressed && down_pressed && !left_pressed && !right_pressed) {
			return 4;
		} else if (!up_pressed && down_pressed && left_pressed && !right_pressed) {
			return 5;
		} else if (!up_pressed && !down_pressed && left_pressed && !right_pressed) {
			return 6;
		} else if (up_pressed && !down_pressed && left_pressed && !right_pressed) {
			return 7;
		} else {
			return -1;
		}
	}
	
	
	
	/**
	 * Diese Methode ueberprueft, ob die Leertaste gedrueckt wurde.
	 * 
	 * @author Frederik Shull
	 * 
	 * @return Gibt den Status der Leertaste als boolean-Wert zurueck
	 */
	
	public static boolean getSpacebar() {
		return spacebar;
	}
	
	
	
	/**
	 * Diese Methode weist der Variable {@link#spacebar} einen neuen Wert zu.
	 * 
	 * @author Frederik Shull
	 * 
	 * @param spcbr Der boolean-Wert, welcher der Variable {@link#spacebar}
	 * zugewiesen werden soll
	 */
	
	public static void setSpacebar(boolean spcbr) {
		spacebar = spcbr;
	}
}
