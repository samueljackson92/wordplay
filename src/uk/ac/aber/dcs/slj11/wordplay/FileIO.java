package uk.ac.aber.dcs.slj11.wordplay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import uk.ac.aber.dcs.slj11.wordplay.datastruct.WordLadderGraph;
import uk.ac.aber.dcs.slj11.wordplay.datastruct.graph.Node;

/**
 * Class to load a list of words from a data file into the program
 * and output generated graphs as Gephi XML.
 * @author Samuel Jackson (slj11@aber.ac.uk)
 *
 */
public class FileIO {
	/**
	 * List of words that are currently loaded into the program.
	 */
	private LinkedList<String> words;
	
	/**
	 * Used to load a set of words from a given data file into the words list.
	 * @param filename The file to read.
	 * @throws IOException
	 */
	public void loadFile(String filename) throws IOException {
		words = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = br.readLine()) != null) {
			words.add(line);
		}
		br.close();
	}
	
	/**
	 * Exports the a graph object into an XML file that can be read by the Gephi graph visualisation program.
	 * @param graph Graph to be exported
	 * @param filename Name of the output file
	 * @throws IOException
	 */
	public void exportToGephi(WordLadderGraph graph, String filename) throws IOException {
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, Node<String>>> it = graph.getIterator();
		sb.append("<gexf xmlns=\"http://www.gexf.net/1.2draft\" version=\"1.2\">\n");
		sb.append("<graph mode=\"static\" defaultedgetype=\"undirected\">\n");
	    sb.append("<nodes>\n");
	    //our nodes
	    while (it.hasNext()) {
	    	Entry<String, Node<String>> entry = it.next();
	    	sb.append("<node id=\"" + entry.getKey() + "\" label=\"" + entry.getKey() + "\" />\n");
	    }
	    sb.append("</nodes>\n");
	    sb.append("<edges>\n");
	    //our edges
	    it = graph.getIterator();
	    int index = 0;
	    while (it.hasNext()) {
	    	Entry<String, Node<String>> entry = it.next();
	    	for (String s : graph.getNeighbors(entry.getKey())) {
	    		sb.append("<edge id=\""+ index +"\" source=\"" + entry.getKey() + "\" target=\"" + s +"\" />\n");
	    		index++;
	    	}
	    	
	    }
	    sb.append("</edges>\n");
	    sb.append("</graph>\n");
	    sb.append("</gexf>\n");

	    String output = sb.toString();
	    
	    write(filename + ".gexf", output);
	}
	
	/**
	 * Returns a list of the currently loaded word file.
	 * @return LinkedList of loaded words
	 */
	public LinkedList<String> getWordList() {
		return words;
	}
	
	/**
	 * General method to output a given string to a given filename.
	 * @param filename Desired name of the output file
	 * @param output The output to write to the file
	 * @throws IOException
	 */
	public void write(String filename, String output) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		out.write(output);
		out.close();
	}
}
