import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/** 
 * Diese Klasse enthaelt Methoden zum Testen der Klasse Main.
 * 
 * @author Christopher Peter
 */

class MainTest {

	/**
	 * Diese Methode testet die Methode doCollide der Klasse Main. Es wird
	 * erwartet, dass das Asteroidenobjekt ast zurueckgegeben wird, da es mit
	 * dem der Spielerobjekt kollidiert.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException 
	 */
	
	@Test
	void testDoCollideSpielerArrayListOfAsteroid() throws IOException {
		Asteroid ast = new Asteroid(true);
		Spieler sp = new Spieler(ast.x,ast.y,0);
		ArrayList<Asteroid> liste = new ArrayList();
		liste.add(ast);
		assertSame(ast, Main.doCollide(sp, liste));	
	}
	
	
	
	/**
	 * Diese Methode testet die Methode doCollide der Klasse Main.
	 * Es wird erwartet, dass das Astroidenobjekt ast nicht von der Methode 
	 * zurueckgegeben wird, da es nicht mit dem Spielerobjekt kollidiert.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException
	 */
	
	@Test
	void testDoCollideSpielerArrayListOfAsteroid2() throws IOException {
		Spieler sp2 = new Spieler(1,1,0);
		Asteroid ast2 = new Asteroid(true);
		ArrayList<Asteroid> liste = new ArrayList();
		liste.add(ast2);
		assertNotSame(ast2, Main.doCollide(sp2, liste));	
	}
	
	
	
	/**
	 * Diese Methode testet die Methode doCollide der Klasse Main. Es wird
	 * erwartet, dass das Asteroidenobjekt ast zurueckgegeben wird, da es
	 * auf die gleichen Koordinaten wie das Laserobjekt gesetzt wird.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException 
	 */
	
	@Test
	void testDoCollideArrayListArrayList() throws IOException {
		Main.ClearLaserList = new ArrayList();
		Asteroid ast = new Asteroid(true);
		Laser laser = new Laser(new Spieler(50, 50, 5));
		ast.x = laser.x;
		ast.y = laser.y;
		ArrayList<Asteroid> liste = new ArrayList();
		ArrayList<Laser> liste2 = new ArrayList();
		liste.add(ast);
		liste2.add(laser);
		assertSame(ast, Main.doCollide(liste2, liste));
	}
	
	
	
	/**
	 * Diese Methode testet die Methode doCollide der Klasse Main.
	 * Es wird erwartet, dass das Asteroidenobjekt ast nicht von der Methode
	 * zurueckgegeben wird, da es nicht mit dem Laserobjekt kollidiert.
	 * 
	 * @author Christopher Peter
	 * 
	 * @throws IOException
	 */
	
	@Test
	void testDoCollideArrayListArrayList2() throws IOException {
		Main.ClearLaserList = new ArrayList();
		Asteroid ast = new Asteroid(true);
		Laser laser = new Laser(new Spieler(50, 50, 5));
		
		ArrayList<Asteroid> liste = new ArrayList();
		ArrayList<Laser> liste2 = new ArrayList();
		liste.add(ast);
		liste2.add(laser);
		assertNotSame(ast, Main.doCollide(liste2, liste));
	}	
}