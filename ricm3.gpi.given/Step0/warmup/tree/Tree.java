package warmup.tree;

import java.awt.Window.Type;

/**
 * This is a tree of named node.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public class Tree extends Node {

	public static final char pathSeparator = '/';
	public static final String pathSeparatorString = "/";

	public Tree() {
		super("");
	}

	/**
	 * Finds a node corresponding to a path in the tree. If the path does not
	 * exists, throws NotFoundException
	 * 
	 * @param path of the searched node
	 * @return the node named by the given path
	 * @throws NotFoundException if the path does not exist
	 */
	public Node find(String path) throws NotFoundException {
		if (path.charAt(0) != pathSeparator) {
			throw new IllegalArgumentException("/");
		}

		String[] tabPath = path.split(pathSeparatorString);
		Node currentNode = this;
		Node child;

		for (int i = 1; tabPath.length > i; i++) {
			child = currentNode.child(tabPath[i]);
			if (child == null) {
				throw new NotFoundException("Wrong path");
			}
			currentNode = child;
		}
		return currentNode;
	}

	/**
	 * Make a path in the tree, leading either to a leaf or to a node.
	 * 
	 * @throws IllegalStateException if the path should be to a leaf but it already
	 *                               exists to a node, of if the path should be to a
	 *                               node but it already exists to a leaf.
	 */
	public Node makePath(String path, boolean isLeaf) {
		String[] tabPath = path.split(pathSeparatorString);
		Node currentNode = this;
		Node child;
		int i;

		for (i = 1; tabPath.length - 1 > i; i++) {
			child = currentNode.child(tabPath[i]);
			if (child == null) {
				Node newNode = new Node(currentNode, tabPath[i]);
				currentNode = newNode;
			} else {
				currentNode = child;
			}
		}
		child = currentNode.child(tabPath[i]);
		if (child == null) {
			if (isLeaf) {
				Leaf newLeaf = new Leaf(currentNode, tabPath[i]);
				return newLeaf;
			} else {
				Node newNode = new Node(currentNode, tabPath[i]);
				return newNode;
			}
		}

		if ((isLeaf && currentNode instanceof Node) || (!isLeaf && currentNode instanceof Leaf)) {
			throw new IllegalStateException("ISE");
		}

		return currentNode;
	}

	public String toString() {
		TreePrinter p = new TreePrinter(this);
		return p.toString();
	}

}
