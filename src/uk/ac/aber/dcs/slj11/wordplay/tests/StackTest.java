package uk.ac.aber.dcs.slj11.wordplay.tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.slj11.wordplay.datastruct.list.Stack;

public class StackTest {

	Stack<String> s;
	@Before
	public void setUp() throws Exception {
		s = new Stack<String>();
	}

	@Test
	public void testPeek() {
		s.push("hello");
		s.push("world");
		assertEquals(s.peek(), "world");
	}
	
	@Test
	public void testPeekEmptyStack() {
		assertNull(s.peek());
	}
	
	@Test
	public void testPeekBoundaryEmptyStack() {
		s.push("hello!");
		assertEquals(s.peek(), "hello!");
	}
	
	@Test
	public void testMultiplePeek() {
		s.push("hello");
		s.push("world");
		s.pop();
		s.push("Bulls!");
		assertTrue(s.peek().equals("Bulls!"));
	}

	@Test
	public void testPush() {
		s.push("hello");
		s.push("World!");
		s.push("food");
		assertEquals(s.peek(), "food");
	}
	
	@Test
	public void testPushBoundaryStack() {
		s.push("hello");
		assertEquals(s.peek(), "hello");
	}

	@Test
	public void testPop() {
		s.push("hello");
		s.push("World!");
		s.push("food");
		s.pop();
		String str = s.pop();
		assertEquals(str, "World!");
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testInvalidRemove() {
		s.pop();
	}
	
	@Test
	public void testBoundaryRemove() {
		s.push("hi");
		String str = s.pop();
		assertEquals(str, "hi");
	}

	@Test
	public void testIsEmpty() {
		assertTrue(s.isEmpty());
	}

	@Test
	public void testSize() {
		s.push("hi");
		s.push("jack");
		s.push("Sam");
		assertTrue(s.size() == 3);
	}
	
	@Test
	public void testBoundarySize(){
		s.push("hi");
		assertTrue(s.size() == 1);
	}
	
	@Test
	public void testEmptySize() {
		assertTrue(s.size() == 0);
	}

	@Test
	public void testClear() {
		s.push("hi");
		s.push("jack");
		s.push("Sam");
		s.clear();
		assertTrue(s.size() == 0);
	}
	
	@Test
	public void testClearEmptyQueue() {
		s.clear();
		assertTrue(s.size() == 0);
	}

	@Test
	public void testToString() {
		s.push("hello");
		s.push("world");
		assertEquals(s.toString(), "[world, hello]");
	}

}
