import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Diese Klasse enthaelt Methoden zum Testen der Klasse Spieler.
 * 
 * @author Christopher Peter
 *
 */

class SpielerTest {
	
	
	
	/**
	 * Diese Methode testet den Konstruktor der Klasse Spieler. Es wird erwartet,
	 * dass das erzeugte Spielerobjekt nicht leer ist.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException
	 */
	
	@Test
	void testSpieler() throws IOException {
		Spieler sp = new Spieler(1,1,0);
		assertNotEquals(null, sp);
		
	}
	
	
	
	/**
	 * Diese Methode testet das Verhalten des Konstruktors bei negativen
	 * Parametern. Werden negative Werte uebergeben, soll das Spielerobjekt
	 * auf die Standardposition gesetzt werden (siehe sp3).
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOExeption
	 */
	
	@Test
	void testSpieler2() throws IOException{
		Spieler sp2 = new Spieler(-1,1,0);								// negative Koordinaten
		Spieler sp3 = new Spieler(16, Main.windowHeight / 2 - 64, 0);	// normale Startposition beim Starten des Spieles
		assertEquals(sp3.x, sp2.x);
		assertEquals(sp3.y, sp2.y);
	}
	
	
	
	/**
	 * Diese Methode testet die Methode movePlayer der Klasse Spieler.
	 * Es wird erwartet, dass sich die Y-Koordinate des Spielerobjekts
	 * um den Wert der Variable speed verändert.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException
	 */
	
	@Test
	void testMovePlayer() throws IOException {
		Spieler sp = new Spieler(50,50,5);
		double y2 = sp.y;
		sp.movePlayer(0);
		assertEquals(y2, sp.y + sp.speed);
	}
	
	
	/**
	 * Diese Methode testet die Methode movePlayer der Klasse Spieler.
	 * Es wird erwartet, dass sich die X-Koordinate des Spielerobjekts 
	 * auf 0 verändert, damit der Spieler den sichtbaren Bereich nicht 
	 * verlässt.
	 * @author Christopher Peter
	 * @throws IOException
	 */
	@Test
	void testMovePlayer2() throws IOException{
		Spieler sp2 = new Spieler(5,5,10);
		double x2 = sp2.x;
		sp2.movePlayer(6);
		assertNotEquals(x2 + sp2.speed, sp2.x);
		assertEquals(0, sp2.x);
	}
}
