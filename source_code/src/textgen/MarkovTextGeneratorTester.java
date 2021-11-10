package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MarkovTextGeneratorTester {
	
	MarkovTextGeneratorLoL gen;
	String textString = "hi there hi Leo";
	String resultString = "hi: there->Leo->\n"
						+ "there: hi->\n"
						+ "Leo: hi->\n";
	
	String textString2 = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
	String resultString2 = "Hello.: Hello->\n"
						+ "Hello: there.->there.->Bob.->\n"
						+ "there.: This->Hello->\n"
						+ "This: is->\n"
						+ "is: a->\n"
						+ "a: test.->\n"
						+ "test.: Hello->\n"
						+ "Bob.: Test->\n"
						+ "Test: again.->\n"
						+ "again.: Hello.->\n";
	@Before
	public void setUp() throws Exception {
		gen = new MarkovTextGeneratorLoL(new Random(42));
	}
	
	@Test
	public void testTrain() {
		try {
			gen.train(null);
			fail("Check train null");
		} 
		catch (NullPointerException e) {		
		}
		
		try {
			gen.train("");
			fail("Check train empty string");
		} 
		catch (IllegalArgumentException e) {		
		}
		
		gen.train(textString);
		assertEquals("Check word list", resultString, gen.toString());
		
		gen.retrain(textString2);
		assertEquals("Check word list", resultString2, gen.toString());
	}
	
	public void testRetrain() {
		try {
			gen.train(null);
			fail("Check train null");
		} 
		catch (NullPointerException e) {		
		}
		
		/*try {
			gen.train("");
			fail("Check train empty string");
		} 
		catch (IllegalArgumentException e) {		
		}*/
		
		gen.train(textString);
		assertEquals("Check word list", resultString, gen.toString());
		
		gen.retrain(textString2);
		assertEquals("Check word list", resultString2, gen.toString());
		
		gen.retrain(textString);
		assertEquals("Check word list", resultString, gen.toString());
	}
}
