package warmup.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a node of a tree. Each node has a name. Therefore, each node is
 * reachable through a path. Each node may be attached an object.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public class Node {
	Node m_parent;
	String m_name;
	List<Node> m_children;
	Object m_attachment;

	/**
	 * @param name
	 * @throws IllegalArgumentException if the name contains the character
	 *                                  Tree.pathSeparator
	 */
	protected Node(String name) {
		if (name.contains(Tree.pathSeparatorString)) {
			throw new IllegalArgumentException("Wrong name");
		}

		m_parent = null;
		m_name = name;
		m_children = new LinkedList<Node>();
		m_attachment = null;
	}

	/**
	 * @param name
	 * @throws IllegalArgumentException if the name contains the character
	 *                                  Tree.pathSeparator
	 */
	public Node(Node parent, String name) {
		if (name.contains(Tree.pathSeparatorString)) {
			throw new IllegalArgumentException("Wrong name");
		}

		for (int i = 0; parent.m_children.size() > i; i++) {
			if (parent.m_children.get(i).m_name == name) {
				throw new IllegalStateException("Wrong name");
			}
		}

		if (parent instanceof Leaf) {
			throw new IllegalStateException("Wrong parent");
		}

		parent.m_children.add(this);

		m_parent = parent;
		m_name = name;
		m_children = new LinkedList<Node>();
		m_attachment = null;
	}

	public String toString() {
		if (m_name == null)
			return "";
		return m_name;
	}

	public Node parent() {
		return m_parent;
	}

	public void attach(Object e) {
		m_attachment = e;
	}

	public Object attachment() {
		return m_attachment;
	}

	public void name(String name) {
		m_name = name;
	}

	public String name() {
		return m_name;
	}

	public String path() {
		String path = "";
		while (m_parent != null) {
			path += Tree.pathSeparatorString + m_name;
		}
		return path;
	}

	public void remove() {
		m_parent.m_children.remove(this);
		m_parent = null;
	}

	public Iterator<Node> children() {
		Iterator<Node> iter = m_children.listIterator();
		return iter;
	}

	public Node child(String name) {
		Node current;
		Iterator<Node> iter = children();

		while (iter.hasNext()) {
			current = iter.next();
			if (current.m_name.equals(name))
				return current;
		}
		return null;
	}

}
