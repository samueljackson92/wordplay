package uk.ac.aber.dcs.slj11.wordplay.util;

import java.util.Comparator;

import uk.ac.aber.dcs.slj11.wordplay.datastruct.graph.Node;

/**
 * Comparator class used to evaluate the priority of word ladder nodes stored in a Java priority queue.
 * Calculates the difference between the distance-cost estimate for each node in the queue.
 * @author Samuel Jackson (slj11@aber.ac.uk)
 *
 */
public class WordLadderComparator implements Comparator<Node<String>> {

	@Override
	public int compare(Node<String> word1, Node<String> word2) {
		return word1.getPathCostEstimate() - word2.getPathCostEstimate();
	}

}
