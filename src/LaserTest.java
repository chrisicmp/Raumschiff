import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/** 
 * Diese Klasse enthaelt Methoden zum Testen der Klasse Laser.
 * 
 * @author Christopher Peter
 */

class LaserTest {

	/**
	 * Diese Methode testet den Konstruktor der Klasse Laser.
	 * Es wird erwartet, dass alle Parameter uebernommen werden
	 * und kein leeres Objekt erzeugt wird.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException
	 */
	
	@Test
	void testLaser() throws IOException {
		Spieler sp = new Spieler(1,1,0);
		Laser laser = new Laser(sp);
		assertNotEquals(null, laser);
	}
	
	
	
	/**
	 * Diese Methode testet die Methode move der Klasse Laser.
	 * Es wird erwartet, dass false zurueckgegeben wird, da sich
	 * das Laserobjekt innerhalb des Spielbereichs befindet.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException
	 */
	
	@Test
	void testLaserMove() throws IOException{
		Spieler sp = new Spieler(1,1,0);
		Laser laser = new Laser(sp);
		assertEquals(false, laser.move());
	}
	
	
	
	/**
	 * Diese Methode testet die Methode move der Klasse Laser.
	 * Es wird erwartet, dass true zurueckgegeben wird, da sich
	 * das Laserobjekt ausserhalb des Spielbereichs befindet.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException
	 */
	
	@Test
	void testLaserMove2() throws IOException {
		Spieler sp2 = new Spieler(1,1,0);
		Laser laser2 = new Laser(sp2);
		laser2.x = Main.windowWidth + laser2.image.getWidth();
		assertEquals(true, laser2.move());
		
	}
}
