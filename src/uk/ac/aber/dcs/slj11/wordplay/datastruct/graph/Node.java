package uk.ac.aber.dcs.slj11.wordplay.datastruct.graph;

import java.util.LinkedList;

/**
 * Represents a single node (vertex) in the graph. Holds data about the particular vertex and
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
	 * Int representing the current cost of the path through the graph to this node. -1 indicates that it is unexplored.
	 */
	private int pathCost = -1;
	
	/**
	 * Int representing the current distance travelled, 
	 * plus the heuristic estimate of how far the goal is from the current node.
	 */
	private int pathCostEstimate = -1;
	
	/**
	 * The key used to reference this node in the graph.
	 */
	private Key key;
	
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

	/**
	 * Get the current estimated path cost for this node.
	 * @return the path cost estimate
	 */
	public int getPathCostEstimate() {
		return pathCostEstimate;
	}

	/**
	 * Set the estimated path cost for this node
	 * @param cost the new estimated path cost
	 */
	public void setPathCostEstimate(int cost) {
		pathCostEstimate = cost;
	}

	/**
	 * Get the key that references this node in the graph.
	 * @return The key that references this node
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * Set the key that references this node in the graph.
	 * @param key the new key to reference this node by.
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * Get the current path cost for this node.
	 * @return the path cost of this node.
	 */
	public int getPathCost() {
		return pathCost;
	}

	/**
	 * Set the path cost for this node.
	 * @param cost the new path cost for this node.
	 */
	public void setPathCost(int cost) {
		pathCost = cost;
	}
}
