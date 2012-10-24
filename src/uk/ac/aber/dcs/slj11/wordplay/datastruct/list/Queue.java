package uk.ac.aber.dcs.slj11.wordplay.datastruct.list;

public class Queue <E> extends AbstractData<E> {
	
	/**
	 * Enqueue an item to the end of the queue
	 * @param obj the item to be queued
	 */
	public void insert(E obj) {
			getList().addLast(obj);
	}
	
	/**
	 * Dequeue an item from the start of the queue
	 * @return the item at the start of the list
	 */
	public E remove() {
		return getList().removeFirst();
	}
	
	/**
	 * Peek at the first item in the queue without removing it
	 * @return the item at the start of the queue
	 */
	public E peek() {
		return getList().peekFirst();
	}
}
