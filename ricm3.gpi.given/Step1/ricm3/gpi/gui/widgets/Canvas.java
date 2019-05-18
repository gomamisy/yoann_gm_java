package ricm3.gpi.gui.widgets;

import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;

/**
 * A canvas to draw on.
 * 
 * The intended usage is to subclass this canvas, usually listening to mouse and
 * keyboard events
 * 
 * The default painting for a canvas is to fill the canvas with the background
 * color. Your subclass may override this behavior.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public class Canvas extends Component {

	public Canvas(Container parent) {
		super(parent);
	}
}
