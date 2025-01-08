import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KeyHandlerTest {

	/**
	 * Diese Methode testet die Methode getDirection der Klasse KeyHandler.
	 * Es wird erwartet, dass 2 zur�ckgegeben wird, da die 2 f�r "nach rechts" steht.
	 * 
	 * @author Christopher Peter
	 */
	
	@Test
	public void testGetDirection() {
		KeyHandler.right_pressed = true;
		
		assertEquals(2, KeyHandler.getDirection());
	}
	
	
	
	/**
	 * Diese Methode testet die Methode getDirection der Klasse KeyHandler.
	 * Es wird erwartet, dass 1 zur�ckgegeben wird, da die 1 f�r "nach oben rechts" steht.
	 * 
	 * @author Christopher Peter
	 */
	
	@Test
	public void testGetDirection2() {
		KeyHandler.up_pressed = true;
		KeyHandler.right_pressed = true;
		
		assertEquals(1, KeyHandler.getDirection());	
	}

}
