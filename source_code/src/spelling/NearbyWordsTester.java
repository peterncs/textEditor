package spelling;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class NearbyWordsTester {
	
	private String dictFile = "data/words.small.txt"; 
	
	NearbyWords speller;
	DictionaryBST smallDict;
	DictionaryBST largeDict; 
	List<String> retList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		smallDict = new DictionaryBST();
		largeDict = new DictionaryBST();
		speller = new NearbyWords(smallDict);
		
		retList = new ArrayList<String>();
		
		// Test case for substitution
		smallDict.addWord("apeel");
		smallDict.addWord("sbeel");
		smallDict.addWord("spell");
		
		// Test case for insertion
		smallDict.addWord("bspeel");
		smallDict.addWord("sipeel");
		smallDict.addWord("speeli");
		
		// Test case for deletion
		smallDict.addWord("peel");
		smallDict.addWord("seel");
		smallDict.addWord("spee");
		
		DictionaryLoader.loadDictionary(largeDict, dictFile);	
	}
	
	@Test
	public void testInsertions() {
		String[] suggestions = {"bspeel", "sipeel", "speeli"};
		speller.insertions("speel", retList, true);
		assertEquals(3, retList.size());
		for (String s: retList) {
			System.out.println(s);
		}
		for (String suggestion: suggestions) {
			assertTrue(retList.contains(suggestion));
		}
	}
	
	@Test
	public void testDeletions() {
		String[] suggestions = {"peel", "seel", "spee"};
		speller.deletions("speel", retList, true);
		assertEquals(3, retList.size());
		for (String suggestion: suggestions) {
			assertTrue(retList.contains(suggestion));
		}
	}
	
	@Test
	public void testSubstitution() {
		String[] suggestions = {"apeel", "sbeel", "spell"};
		speller.substitution("speel", retList, true);
		assertEquals(3, retList.size());
		for (String suggestion: suggestions) {
			assertTrue(retList.contains(suggestion));
		}
	}
	
	@Test
	public void testDistanceOne() {
		String[] suggestions = {"apeel", "sbeel", "spell",
								"bspeel", "sipeel", "speeli",
								"peel", "seel", "spee"};
		retList = speller.distanceOne("speel", true);
		assertEquals(9, retList.size());
		for (String suggestion: suggestions) {
			assertTrue(retList.contains(suggestion));
		}
	}
}
