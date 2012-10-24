package uk.ac.aber.dcs.slj11.wordplay.tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.slj11.wordplay.datastruct.list.Queue;

public class QueueTest {

	Queue<String> q;
	@Before
	public void setUp() throws Exception {
		q = new Queue<String>();
	}

	@Test
	public void testPeek() {
		q.insert("hello");
		q.insert("world");
		assertEquals(q.peek(), "hello");
	}
	
	@Test
	public void testPeekEmptyQueue() {
		assertNull(q.peek());
	}
	
	@Test
	public void testPeekBoundaryEmptyQueue() {
		q.insert("hello!");
		assertEquals(q.peek(), "hello!");
	}
	
	@Test
	public void testMultiplePeek() {
		q.insert("hello");
		q.insert("world");
		q.remove();
		q.insert("Bulls!");
		assertTrue(q.peek().equals("world"));
	}

	@Test
	public void testInsert() {
		q.insert("hello");
		q.insert("World!");
		q.insert("food");
		assertEquals(q.peek(), "hello");
	}
	
	@Test
	public void testInsertBoundaryQueue() {
		q.insert("hello");
		assertEquals(q.peek(), "hello");
	}

	@Test
	public void testRemove() {
		q.insert("hello");
		q.insert("World!");
		q.insert("food");
		q.remove();
		String s = q.remove();
		assertEquals(s, "World!");
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testInvalidRemove() {
		q.remove();
	}
	
	@Test
	public void testBoundaryRemove() {
		q.insert("hi");
		String s = q.remove();
		assertEquals(s, "hi");
	}

	@Test
	public void testIsEmpty() {
		assertTrue(q.isEmpty());
	}

	@Test
	public void testSize() {
		q.insert("hi");
		q.insert("jack");
		q.insert("Sam");
		assertTrue(q.size() == 3);
	}
	
	@Test
	public void testBoundarySize(){
		q.insert("hi");
		assertTrue(q.size() == 1);
	}
	
	@Test
	public void testEmptySize() {
		assertTrue(q.size() == 0);
	}

	@Test
	public void testClear() {
		q.insert("hi");
		q.insert("jack");
		q.insert("Sam");
		q.clear();
		assertTrue(q.size() == 0);
	}
	
	@Test
	public void testClearEmptyQueue() {
		q.clear();
		assertTrue(q.size() == 0);
	}

	@Test
	public void testToString() {
		q.insert("hello");
		q.insert("world");
		assertEquals(q.toString(), "[hello, world]");
	}

}
