package ricm3.gpi.gui.layout;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ricm3.gpi.gui.Graphics;

/**
 * This is a container within a tree of containers and components. A container
 * is a component that has children components. Children components are painted
 * on top of their parent container.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public class Container extends Component {
	protected List<Component> m_children;

	Container() {
		m_parent = null;
		m_children = new LinkedList<Component>();
		m_x = 0;
		m_y = 0;
		m_width = 0;
		m_height = 0;
	}

	public Container(Container parent) {
		super(parent);
		parent.m_children.add(this);
		m_parent = parent;
		m_children = new LinkedList<Component>();
		m_x = 0;
		m_y = 0;
		m_width = 0;
		m_height = 0;
	}

	/**
	 * @return the number of components that are children to this container
	 */
	public int childrenCount() {
		if (m_children != null) {
			return m_children.size();
		}
		return 0;
	}

	/**
	 * @return the component indexed by the given index.
	 */
	public Component childrenAt(int i) {
		return m_children.get(i);
	}

	/**
	 * Select the component, on top, at the given location. The location is given in
	 * local coordinates. Reminder: children are above their parent.
	 * 
	 * @param x
	 * @param y
	 * @return this selected component
	 */
	public Component select(int x, int y) {
		if (inside(x, y)) {
			Component courrentComp;
			Component resComp = this;
			Iterator<Component> iter = m_children.listIterator();
			while (iter.hasNext()) {
				courrentComp = iter.next();
				if (courrentComp.inside(x, y)) {
					resComp = courrentComp.select(x, y);
				}
			}
			return resComp;
		} else {
			return null;
		}
	}

	/**
	 * Painting a container is a two-step process in order to paint children
	 * components above. - First, the container paints itself. - Second, the
	 * container paints its children
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Component courrentComp;
		Iterator<Component> iter = m_children.listIterator();
		while (iter.hasNext()) {
			courrentComp = iter.next();
			Graphics tmpGraph = g.create(courrentComp.m_x, courrentComp.m_y, courrentComp.m_width, courrentComp.m_height);
			courrentComp.paint(tmpGraph);
			tmpGraph.dispose();
		}

	}

}
