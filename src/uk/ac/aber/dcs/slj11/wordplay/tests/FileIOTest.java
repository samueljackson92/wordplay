package uk.ac.aber.dcs.slj11.wordplay.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.slj11.wordplay.util.FileIO;

public class FileIOTest {

	FileIO fio;
	@Before
	public void setUp() throws Exception {
		fio = new FileIO();
	}
	
	@Test
	public void testLoadFile() {
		try {
			fio.loadFile("dict3.dat");
		} catch (IOException e) {
			fail("Exception throw!");
		}
	}
	
	@Test(expected=IOException.class)
	public void testLoadNonExistantFile() throws IOException{
		fio.loadFile("dict1.dat");
	}

	@Test
	public void testGetWordList() {
		try {
			fio.loadFile("dict3.dat");
			LinkedList<String> list = fio.getWordList();
			assertTrue(!list.isEmpty());
		} catch (IOException e) {
			fail("Exception thrown!");
		}
	}
	
	@Test
	public void testGetEmptyWordList(){
		assertNull(fio.getWordList());
	}

	@Test
	public void testValidWrite() {
		try {
			fio.write("hello", "test.txt");
		} catch (IOException e) {
			fail("Exception thrown!");
		}
	}

}
