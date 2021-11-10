/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		// test remove element from empty list, should throw an exception
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test remove element with index out of bound, should throw an exception
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// Remove first element
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check last element is correct ", (Integer)42, list1.get(1));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		list1.add(0,65);
		
		// Remove middle element
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 21, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)65, list1.get(0));
		assertEquals("Remove: check last element is correct ", (Integer)42, list1.get(1));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		list1.add(1,21);
		
		// Remove last element
		int c = list1.remove(2);
		assertEquals("Remove: check c is correct ", 42, c);
		assertEquals("Remove: check element 0 is correct ", (Integer)65, list1.get(0));
		assertEquals("Remove: check last element is correct ", (Integer)21, list1.get(1));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		list1.add(42);
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			emptyList.add(null);
			fail("Check null element insertion");
		}
		catch (NullPointerException e) {
		}
		
		assertEquals("AddEnd: check size is correct", 2, shortList.size());
		assertEquals("AddEnd: check last element", "B", shortList.get(shortList.size()-1));
		assertEquals("AddEnd: check element 0 is correct", "A", shortList.get(0));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: check empty list", 0, emptyList.size());
		assertEquals("Size: check long list", LONG_LIST_LENGTH, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		// Check null element insertion
		try {
			emptyList.add(0, null);
			fail("Check null element insertion");
		}
		catch (NullPointerException e) {
		}
		
		// test add element at negative index
		try {
			list1.add(-1,99);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test add element at index out of bound
		try {
			list1.add(4,99);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		shortList.add(0, "C");
		assertEquals("AddAtIndex: check size is correct", 3, shortList.size());
		assertEquals("AddAtIndex: check element at index", "C", shortList.get(0));
		assertEquals("AddAtIndex: check last element", "B", shortList.get(shortList.size()-1));
		assertEquals("AddAtIndex: check element 0 is correct", "C", shortList.get(0));
		shortList.remove(0);
		
		shortList.add(1, "C");
		assertEquals("AddAtIndex: check size is correct", 3, shortList.size());
		assertEquals("AddAtIndex: check element at index", "C", shortList.get(1));
		assertEquals("AddAtIndex: check last element", "B", shortList.get(shortList.size()-1));
		assertEquals("AddAtIndex: check element 0 is correct", "A", shortList.get(0));
		shortList.remove(1);
		
		shortList.add(2, "C");
		assertEquals("AddAtIndex: check size is correct", 3, shortList.size());
		assertEquals("AddAtIndex: check element at index", "C", shortList.get(2));
		assertEquals("AddAtIndex: check last element", "C", shortList.get(shortList.size()-1));
		assertEquals("AddAtIndex: check element 0 is correct", "A", shortList.get(0));
		shortList.remove(2);
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		// Check set null element 
		try {
			list1.set(0, null);
			fail("Check null element insertion");
		}
		catch (NullPointerException e) {
		}
		
		// Check set empty list
		try {
			emptyList.set(0, 99);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// Check set element at negative index
		try {
			list1.set(-1,99);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		shortList.set(0, "C");
		assertEquals("Set: check size is correct", 2, shortList.size());
		assertEquals("Set: check element at index", "C", shortList.get(0));
		assertEquals("Set: check last element", "B", shortList.get(shortList.size()-1));
		shortList.set(0, "A");
		
		list1.set(1, 99);
		assertEquals("Set: check size is correct", 3, list1.size());
		assertEquals("Set: check element at index", (Integer)99, list1.get(1));
		assertEquals("Set: check first element", (Integer)65 , list1.get(0));
		assertEquals("Set: check last element", (Integer)42 , list1.get(list1.size()-1));
		list1.set(1, 21);
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
