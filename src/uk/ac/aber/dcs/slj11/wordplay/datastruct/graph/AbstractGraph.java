package uk.ac.aber.dcs.slj11.wordplay.datastruct.graph;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * Generic implementation of a graph data structure using an adjacency list to store a 
 * set of keys which point to a node object, which in turn has a set of keys listing a
 * given nodes neighbours.
 * @author Samuel Jackson (slj11@aber.ac.uk)
 *
 * @param <Key> The type of the key used to reference nodes
 */
public abstract class AbstractGraph<Key> {
	/**
	 * Hashtable that stores a set of keys and a node which represents the graph
	 */
	private Hashtable<Key, Node<Key>> nodes;
	
	/**
	 * Initialise the Graph
	 */
	public AbstractGraph() {
		nodes = new Hashtable<Key, Node<Key>>();
	}
	
	/**
	 * Create a new node using the given key and add it to the graph.
	 * @param key used to create the key for the node
	 */
	public void addNode(Key key) {
		if(!isNode(key)) {
			nodes.put(key, new Node<Key>());
		}
	}
	
	/**
	 * Checks if the given key is a node in the graph
	 * @param key that may be in the graph
	 * @return whether the node exists
	 */
	public boolean isNode(Key key) {
		return nodes.containsKey(key);
	}
	
	/**
	 * Return the total number of nodes in the graph
	 * @return total node count
	 */
	public int numberOfNodes() {
		return nodes.size();
	}
	
	/**
	 * Return the list of nodes
	 * @return the node list
	 */
	public Hashtable<Key, Node<Key>> getNodes() {
		return nodes;
	}
	
	/**
	 * Return an iterator for the node list
	 * @return iterator for the node list
	 */
	public Iterator<Entry<Key, Node<Key>>> getIterator() {
		return getNodes().entrySet().iterator();
	}
	
	/**
	 * Set all of the distances in the Graph back to -1.
	 */
	public void resetGraph() {
		Iterator<Entry<Key,Node<Key>>> it = getIterator();
		while(it.hasNext()) {
			Entry<Key,Node<Key>> e = it.next();
			e.getValue().setVisited(false);
			e.getValue().setPathCost(-1);
			e.getValue().setPathCostEstimate(-1);
		}
	}
	
	/**
	 * Get a list of the neighbours of a given word.
	 * @param key to find the node
	 * @return LinkedList neighbours to this node
	 */
	public LinkedList<Key> getNeighbors(Key key) {
		return getNodes().get(key).getNeighbours();
	}
	
	/**
	 * Set the list of neighbours for a given word.
	 * @param key for the desired node
	 * @param neighbours List of neighbours to assign to node
	 */
	public void setNeighbours(Key key, LinkedList<Key> neighbours) {
		getNodes().get(key).setNeighbours(neighbours);
	}
	
	/**
	 * Set that the given node has been visited.
	 * @param key reference to the node
	 */
	public void setNodeVisited(Key key){
		getNodes().get(key).setVisited(true);
	}
	
	/**
	 * Check if the given node has been visited.
	 * @param key reference to the node
	 * @return whether the node has been visited
	 */
	public boolean nodeIsVisited(Key key) {
		return getNodes().get(key).isVisited();
	}
	
	/**
	 * Method to get a node from the graph.
	 * @param key reference to the node in the graph.
	 * @return the node reference by the given key.
	 */
	public Node<Key> getNode(Key key) {
		return nodes.get(key);
	}
	
	/**
	 * Get the path cost of the given node.
	 * @param key reference to the node.
	 * @return the path cost of the referenced node
	 */
	public int getPathCost(Key key) {
		return nodes.get(key).getPathCost();
	}
	
	/**
	 * Set the path cost of a given node.
	 * @param key reference to the node.
	 * @param cost new cost of reaching this node.
	 */
	public void setPathCost(Key key, int cost) {
		nodes.get(key).setPathCost(cost);
	}
	
	/**
	 * Get the distance plus cost estimate of the given node.
	 * @param key reference to the node.
	 * @return the distance-cost estimate of this node.
	 */
	public int getPathCostEstimate(Key key) {
		return nodes.get(key).getPathCostEstimate();
	}
	
	/**
	 * Set the distance plus cost estimate for the given node.
	 * @param key reference to the given node.
	 * @param cost the new distance-cost estimate of this node.
	 */
	public void setPathCostEstimate(Key key, int cost) {
		nodes.get(key).setPathCostEstimate(cost);
	}
}
