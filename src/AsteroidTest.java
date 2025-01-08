import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AsteroidTest {

	/**
	 * Diese Methode testet den Konstruktor der Klasse Asteroid.
	 * Es wird erwartet, dass das Objekt ast nicht leer ist.
	 * 
	 * @author Christopher Peter
	 */
	
	@Test
	void testAsteroid() {
		Asteroid ast = new Asteroid(true);
		assertNotEquals(null, ast);
	}
	
	
	
	/**
	 * Diese Methode testet die Methode move der Klasse Asteroid.
	 * Es wird erwartet, dass sich die X-Koordinate des Asteroidenobjekts
	 * um den Wert der Variable speed verändert.
	 * 
	 * @author Christopher Peter
	 */
	
	@Test
	void testMove() {
		Asteroid ast = new Asteroid(true);
		double x2 = ast.x;
		ast.speed = 10;
		ast.move();
		assertNotEquals(x2, ast.x);
	}
}
