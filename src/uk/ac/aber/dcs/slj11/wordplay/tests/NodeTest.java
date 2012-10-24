package uk.ac.aber.dcs.slj11.wordplay.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.slj11.wordplay.datastruct.graph.Node;

public class NodeTest {

	Node<String> n;
	
	@Before
	public void setUp() throws Exception {
		n = new Node<String>();
	}

	@Test
	public void testNode() {
		n = new Node<String>();
		assertNotNull(n.getNeighbours());
	}

	@Test
	public void testIsVisited() {
		assertFalse(n.isVisited());
	}

	@Test
	public void testSetVisited() {
		n.setVisited(true);
		assertTrue(n.isVisited());
	}
	
	@Test
	public void testAddNeighbour(){
		n.addNeighbour("hi");
		assertEquals(n.getNeighbours().size(), 1);
	}

	@Test
	public void testAddNeighbours() {
		n.addNeighbour("hello");
		n.addNeighbour("world");
		n.addNeighbour("mmmm");
		assertEquals(n.getNeighbours().size(), 3);
	}

	@Test
	public void testGetNeighbours() {
		n.addNeighbour("hello");
		assertEquals(n.getNeighbours().size(), 1);
	}

	@Test
	public void testSetNeighbours() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("hi");
		list.add("hello");
		list.add("buenos");
		n.setNeighbours(list);
		assertEquals(n.getNeighbours().size(), 3);
	}

}
