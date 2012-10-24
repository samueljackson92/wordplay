package uk.ac.aber.dcs.slj11.wordplay.datastruct.list;

import java.util.LinkedList;

public abstract class AbstractData<E> {
	/**
	 * List of data stored in this list
	 */
	private LinkedList<E> list = new LinkedList<E>();
	
	protected LinkedList<E> getList() {
		return list;
	}
	
	/**
	 * Checks if the list is empty or not
	 * @return If the list is empty or not
	 */
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	/**
	 * Gets the size of the list
	 * @return list size as an int
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Empty all elements from the list
	 */
	public void clear() {
		list.clear();
	}
	
	/**
	 * Convert the items in the list to a printable string
	 */
	public String toString() {
		return list.toString();
	}
	
	/**
	 * Peek at the item in the data list.
	 * @return the item peeked at.
	 */
	public abstract E peek();
}
