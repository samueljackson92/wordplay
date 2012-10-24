package uk.ac.aber.dcs.slj11.wordplay.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.slj11.wordplay.FileIO;
import uk.ac.aber.dcs.slj11.wordplay.datastruct.WordLadderGraph;
import uk.ac.aber.dcs.slj11.wordplay.datastruct.list.Stack;

public class WordLadderGraphTest {

	private WordLadderGraph g;
	
	@Before
	public void setUp() throws Exception {
		g = new WordLadderGraph();
		FileIO fio = new FileIO();
		fio.loadFile("dict4.dat");
		g.makeGraph(fio.getWordList());
	}
	
//	@Test
//	public void testMakeGraph(){
//		makeSmallGraph();
//		assertNotNull(g.getNodes());
//	}
//	
//	@Test
//	public void testMakeGraphCorrectSize() {
//		makeSmallGraph();
//		assertEquals(g.getNodes().size(), 4);
//	}
//	
//	@Test
//	public void testGenerate() {
//		Stack<String> path = g.generate("bed", 3);
//		assertEquals(path.size(), 3);
//	}
//	
//	@Test
//	public void testValidBoundaryDiscovery(){
//		Stack<String> path = g.generate("bed", 492);
//		assertEquals(path.size(), 492);
//		
//	}
//
//	@Test
//	public void testInvalidBoundaryGenerate() {
//		Stack<String> path = g.generate("bed", 494);
//		assertEquals(path.size(), 0);
//	}
//	
//	@Test
//	public void testInvalidGenerate() {
//		Stack<String> path = g.generate("bed", 1000);
//		assertEquals(path.size(), 0);
//	}
//	
//	@Test(expected=NullPointerException.class)
//	public void testInvalidInputGenerate() {
//		g.generate("fight", 10);
//		fail("Should have thrown exception!");
//	}

	@Test
	public void testValidDiscovery() {
		Stack<String> path = g.discovery("head", "foot");
		System.out.println(path);
		assertTrue(path.size() == 6);
	}
//	
//	@Test
//	public void testInvalidDiscovery() {
//		Stack<String> path = g.discovery("bin", "sik");
//		assertTrue(path.isEmpty());
//	}
//	
//	@Test(expected=NullPointerException.class)
//	public void testInvalidInputDiscovery() {
//		g.discovery("flight", "");
//		fail("Should have thrown exception!");
//	}
//	
	////////////////////////////////////////
	//End of JUnit Test Cases
	////////////////////////////////////////
	
	private void makeSmallGraph(){
		g = new WordLadderGraph();
		LinkedList<String> list = new LinkedList<String>();
		list.add("hi");
		list.add("he");
		list.add("ho");
		list.add("el");
		g.makeGraph(list);
	}

}
