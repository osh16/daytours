import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class TestRatingController {

	@Test
	public void testgetStar() {
		RatingController testing = new RatingController();
		double output = testing.getStar(5);
		assertEquals(output,5);
	}
	
	@Test
	public void testsetRating() {
		RatingController testing = new RatingController();
		String output = testing.setRating("Geggjuð ferð!");
		assertTrue(50>output.length());
	}
	
	@Before
	public void testgetRatingfyrir(){
		//Nær úr db rating fyrir einhvern tour
	}
	@After
	public void testgetRatingeftir() {
		//Skilar rating ur db
	}
	
	
	@Test
	public void testgetRating() {
		//ahugar hvort það komi út strengur
		RatingController testing = new RatingController();
		String output = testing.getRating();
		assertEquals(output,"");
		
	}
}
