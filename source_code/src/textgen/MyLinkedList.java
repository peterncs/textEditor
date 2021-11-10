package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
			throw new NullPointerException("MyLinkedList cannot store null pointers");
		}
		LLNode<E> newNode = new LLNode<E>(element, tail.prev, tail);
		tail.prev.next = newNode;
		tail.prev = newNode;
		size += 1;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{	
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bound: "+index);
		}
		
		LLNode<E> curr = head;
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
		}
		return curr.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null) {
			throw new NullPointerException("MyLinkedList cannot store null pointers");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bound: "+index);
		}
		
		LLNode<E> curr = head;
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
		}
		
		LLNode<E> newNode = new LLNode<E>(element, curr.prev, curr);
		curr.prev.next = newNode;
		curr.prev = newNode;
		size += 1;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bound: "+index);
		}
		LLNode<E> curr = head;
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
		}
		curr.prev.next = curr.next;
		curr.next.prev = curr.prev;
		size -= 1;
		return curr.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException("MyLinkedList cannot store null pointers");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bound: "+index);
		}
		
		LLNode<E> curr = head;
		for (int i = 0; i <= index; i++) {
			curr = curr.next;
		}
		curr.data = element;
		return curr.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode prev, LLNode next) 
	{
		this.data = e;
		this.prev = prev;
		this.next = next;
	}

}
