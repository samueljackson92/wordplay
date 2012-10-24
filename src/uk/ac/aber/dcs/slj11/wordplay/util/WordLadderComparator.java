package uk.ac.aber.dcs.slj11.wordplay.util;

import java.util.Comparator;

import uk.ac.aber.dcs.slj11.wordplay.datastruct.graph.Node;

public class WordLadderComparator implements Comparator<Node<String>> {

	@Override
	public int compare(Node<String> word1, Node<String> word2) {
		return word1.getPathCostEstimate() - word2.getPathCostEstimate();
	}

}
