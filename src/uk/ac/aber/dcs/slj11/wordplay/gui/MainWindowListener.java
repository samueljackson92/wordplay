package uk.ac.aber.dcs.slj11.wordplay.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import uk.ac.aber.dcs.slj11.wordplay.datastruct.WordLadderGraph;
import uk.ac.aber.dcs.slj11.wordplay.util.FileIO;

/**
 * Listener for the MainWindow.
 * Calls to create and traverse the graph, export data to files
 * and update the main window.
 * @author Samuel Jackson (slj11@aber.ac.uk)
 *
 */
public class MainWindowListener implements ActionListener {

	/**
	 * Pointer to the Main Window of the application
	 */
	private MainWindow window;
	
	/**
	 * File read/write object to import and export files.
	 */
	private final FileIO fileio = new FileIO();
	
	/**
	 * Pointer to the created object
	 */
	private WordLadderGraph graph;
	
	/**
	 * JFileChooser object used to select the input file.
	 */
	private final JFileChooser fileChooser = new JFileChooser();
	
	/**
	 * Initialise the listener with a pointer back to the Main Window
	 * @param window
	 */
	public MainWindowListener(MainWindow window) {
		this.window = window;
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Data Files (.dat)", "dat"));
	}
	
	/**
	 * Respond to actions in the main window
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();
		
		//exit the program
		if(command.equals("exit")) {
			 
			 System.exit(0);
		
		//build graph from file 
		} else if(command.equals("build")) {
			 
			if (loadFile()) {
				build();
			}
		
		//build or rebuild the graph from the loaded file list
		} else if (command.equals("rebuild")) {
			
			build();
		
		//export the graph object as a Gephi XML file format
		} else if(command.equals("export")){
			 
			 if(graph != null) {
				 String filename = JOptionPane.showInputDialog(window, "Enter filename:", "Export Graph", JOptionPane.QUESTION_MESSAGE);
				 if(filename != null && !filename.isEmpty()) {
					 try {
						 fileio.exportToGephi(graph, filename);
						 window.updateOutput("\nEXPORTING GRAPH");
						 window.updateOutput("--------------------------------------------------------------");
						 window.updateOutput("Successfully exported graph to " + filename + ".gexf");
					 } catch (IOException e) {
				 		JOptionPane.showMessageDialog(window, "IOException occured: " + e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
					 }
				 }
			 } else {
				 JOptionPane.showMessageDialog(window, "No graph has been created!", "No Graph!", JOptionPane.INFORMATION_MESSAGE);
			 }
		
		//generate a path through the graph
		 } else if (command.equals("generate")) {
			 
			 //check if the graph has been built
			 if(graph != null) {
				 String strSteps = "";
				 boolean invalidInput = false;
				 int steps = 0;
				 
				 //get a valid word and valid number of steps
				 String word = getValidWord("Input starting word:");
				 if (word == null) {
					 invalidInput = true;
				 } else {
					 strSteps = JOptionPane.showInputDialog(window, "Input number of steps:", "Input Steps", JOptionPane.QUESTION_MESSAGE);
					 if(strSteps != null) {
						 try {
							 steps = Integer.parseInt(strSteps);
						 } catch (NumberFormatException e) {
							 JOptionPane.showMessageDialog(window, "That wasn't a number!", "Number Format Exception!", JOptionPane.ERROR_MESSAGE);
							 invalidInput = true;
						 }
					 }
				 }
				 
				 if(!invalidInput) {
					 //generate the path and update main window
					 window.updateOutput("\nGENERATING PATH");
					 window.updateOutput("--------------------------------------------------------------");
					 window.updateOutput("Finding shortest path from " + word + " with "+ steps + " steps...");
					 Stack<String> path = graph.generate(word, steps);
					 if (!path.isEmpty()) {
						 Collections.reverse(path);
						 window.updateOutput(path.toString());
					 } else {
						 window.updateOutput("Unable to find path. Not enough unique words in graph!");
					 }
				 }
				 
			 } else {
				 JOptionPane.showMessageDialog(window, "No Graph has been created!", "No Graph!", JOptionPane.INFORMATION_MESSAGE);
			 }
			 
		//find a path between two nodes in the graph
		 } else if (command.equals("discover")) {
			 //check if the graph has been built
			 if(graph != null) {
				 boolean invalidInput = false;
				 String start, finish = "";
				 start = getValidWord("Input starting word:");
				 
				 //check if both words are valid
				 if (start == null) {
					 invalidInput = true;
				 } else {
					 finish = getValidWord("Input target word:");
				 }
				 if (finish == null) invalidInput = true;
				 
				 if(!invalidInput) {
					 
					 //discover a path between the given nodes, then update main window
					 window.updateOutput("\nDISCOVERING PATH");
					 window.updateOutput("--------------------------------------------------------------");
					 window.updateOutput("Finding path with from " + start + " to "+ finish + "...");
					 Stack<String> path = graph.discovery(start, finish);
					 if (!path.isEmpty()) {
						 window.updateOutput("Optimal solution found with " + path.size() + " nodes");
						 Collections.reverse(path);
						 window.updateOutput(path.toString());
					 } else {
						 window.updateOutput("Unable to find path. Words are part of unconnected graphs!");
					 }
				 }
				 
			 } else {
				 JOptionPane.showMessageDialog(window, "No Graph has been created!", "No Graph!", JOptionPane.INFORMATION_MESSAGE);
			 }
			 
		//Show about dialog
		 } else if (command.equals("about")) {
			 JOptionPane.showMessageDialog(window, "WordPlay\nWord Ladder Graph Generator.\nCreated by Samuel Jackson \u00a9 2012", "About WordPlay", JOptionPane.INFORMATION_MESSAGE);
		 
		//Show help dialog
		 } else if (command.equals("save")) {
			String filename = JOptionPane.showInputDialog(window, "Enter filename:", "Export Log", JOptionPane.QUESTION_MESSAGE);
			if(filename != null && !filename.isEmpty()) {
				 try {
					 fileio.write(filename, window.getLog());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(window, "IOException occured: " + e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
				}
			}
		 }
	}
	
	/**
	 * Prompts the user to select a file using JFileChooser
	 * The loads the given file into the program
	 */
	private boolean loadFile() {
		//get user to choose a file
		 int val = fileChooser.showOpenDialog(window);
		 boolean result = false;
		 //check if they selected a file
		 if(val == JFileChooser.APPROVE_OPTION) {
			 File file = fileChooser.getSelectedFile();
			 
			 //load the selected file
			 try {
				fileio.loadFile(file.getAbsolutePath());
				result = true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(window, "IOException: Could not read the selected file!\n" + e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
			}
		 }
		 
		 return result;
	}

	/**
	 * Builds the graph form a given word list
	 */
	private void build() {
		boolean invalidInput = false;
		
		//check if we have a word list loaded, else load one
		 if(fileio.getWordList() == null) {
			 int option = JOptionPane.showConfirmDialog(window, "No input file loaded! Load one now?", "No Word List", JOptionPane.YES_NO_OPTION);
			 if(option == JOptionPane.OK_OPTION) {
				 loadFile();
			 } else {
				 invalidInput = true;
			 }
		 }
		 
		 if(!invalidInput) {
			 //build the graph
			 window.updateOutput("\nBUILDING GRAPH");
			 window.updateOutput("--------------------------------------------------------------");
			 graph = new WordLadderGraph();
			 graph.makeGraph(fileio.getWordList());
			 window.updateOutput("Generated graph with " + graph.numberOfNodes() + " nodes!");
		 }
	}
	
	/**
	 * Check that the word the user input is a valid choice
	 * @param message The word that the user entered
	 * @return Whether the word is valid or not
	 */
	private String getValidWord(String message) {
		boolean invalidInput = false;
		//get word
		String word = JOptionPane.showInputDialog(window, message, "Input Word", JOptionPane.QUESTION_MESSAGE); 
		
		//check if the word exits in the word list
		if(word != null && !graph.isNode(word)){
			JOptionPane.showMessageDialog(window, "Not a valid word!", "Invalid word", JOptionPane.ERROR_MESSAGE);
			invalidInput = true;
		}
		
		return (!invalidInput) ? word : null;
	}
}
