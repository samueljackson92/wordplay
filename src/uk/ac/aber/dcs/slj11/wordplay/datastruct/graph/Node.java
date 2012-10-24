package uk.ac.aber.dcs.slj11.wordplay.datastruct.graph;

import java.util.LinkedList;

/**
 * Represent a single node (vertex) in the graph. Holds data about the particular vertex and
 * holds a list of neighbour nodes.
 * @author Samuel Jackson (slj11@aber.ac.uk)
 *
 */
public class Node<Key> {
	/**
	 * List of neighbour nodes which are connected to this vertex
	 */
	private LinkedList<Key> neighbours;
	
	/**
	 * Stores whether this node has been visited in any given graph traversal.
	 */
	private boolean visited =false;
	
	/**
	 * Creates a new node
	 */
	public Node () {
		neighbours = new LinkedList<Key>();
	}
	
	/**
	 * Check if this node has been visited during a traversal of the graph.
	 * @return whether this node has been visited
	 */
	public boolean isVisited() {
		return visited;
	}
	
	/**
	 * Set the visited state of this node.
	 * @param visited the new state of the node
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Add a new node to the list of neighbours
	 * @param neighbour Node to be linked with the current node
	 */
	public void addNeighbour(Key neighbour) {
		neighbours.add(neighbour);
	}
	
	/**
	 * Get the list of neighbours stored in this node.
	 * @return LinkedList of neighbour nodes
	 */
	public LinkedList<Key> getNeighbours() {
		return neighbours;
	}
	
	/**
	 * Set the list of neighbours for this node.
	 * @param neighbours List of neighbours which have an edge to this node
	 */
	public void setNeighbours(LinkedList<Key> neighbours) {
		this.neighbours = neighbours;
	}
}
