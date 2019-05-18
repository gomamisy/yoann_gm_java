package warmup.tree;

import java.util.Iterator;

/**
 * This is a leaf of a tree, that is, a node that cannot have child nodes.
 * Adding a node or a leaf to a leaf must throw an illegal state exception.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public class Leaf extends Node {

	public Leaf(Node parent, String name) {
		super(parent, name);
	}

	@Override
	public Iterator<Node> children() {
		Iterator<Node> iter = m_children.listIterator();
		if (iter.hasNext()) {
			throw new IllegalStateException();
		}
		return iter;
	}

	@Override
	public Node child(String name) {
		return null;
	}

}
