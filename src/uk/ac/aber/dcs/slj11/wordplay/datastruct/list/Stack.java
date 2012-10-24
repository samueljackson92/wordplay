package uk.ac.aber.dcs.slj11.wordplay.datastruct.list;


/**
 * Stack class wraps a linked getList() to encapsulate and
 * restrict positional access.
 * @author Samuel Jackson (slj11@aber.ac.uk)
 *
 * @param <E> The type of the desired stack
 */
public class Stack<E> extends AbstractData<E> {
	
	/**
	 * Push a new item onto the stack
	 * @param obj item to be added to the stack
	 */
	public void push(E obj){
		getList().push(obj);
	}
	
	/**
	 * Peek at the top element on the stack
	 * @return The element on the top of the stack
	 */
	public E peek() {
		return getList().peek();
	}
	
	/**
	 * Pop the top element off the stack
	 * @return The element on the top of the stack
	 */
	public E pop() {
		return getList().pop();
	}
}
