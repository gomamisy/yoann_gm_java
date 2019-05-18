package ricm3.gpi;

import ricm3.gpi.gui.Graphics;
import ricm3.gpi.gui.KeyListener;
import ricm3.gpi.gui.MouseListener;
import ricm3.gpi.gui.WindowListener;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.widgets.Canvas;
import ricm3.gpi.gui.layout.Location;

/**
 * A canvas to draw lines freely.
 *
 * To draw a line, two possibilities.
 *
 * 1) Press down the left button and move the mouse The drawing ends when the
 * left button is released.
 * 
 * 2) Press the space-bar key on the keyboard toggles the drawing mode. Moving
 * the mouse draws if drawing is on.
 * 
 * To clear the canvas, press the key 'c' on your keyboard.
 *
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public class DrawCanvas extends Canvas {

	int x1, y1, x2, y2;

	public DrawCanvas(Container parent) {
		super(parent);
		this.setMouseListener(new Mouse_drawing(this));
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(m_bgColor);
		g.fillRect(0, 0, m_width, m_height);
		g.setColor(m_fgColor);
		g.drawLine(x1, y1, x2, y2);
	}

	public static class Mouse_drawing implements MouseListener {

		boolean m_clic;
		DrawCanvas m_canv;
		boolean mode_dessin;

		public Mouse_drawing(DrawCanvas c) {
			m_canv = c;
			mode_dessin = false;
		}

		@Override
		public void mouseMoved(int x, int y) {
			if (mode_dessin == true) {
				Location l = new Location(x, y);
				m_canv.toLocal(l);
				m_canv.x2 = l.x();
				m_canv.y2 = l.y();
				if (m_clic = true) {
					m_canv.repaint();
				}
			}
		}

		@Override
		public void mousePressed(int x, int y, int button) {
			m_clic = true;
			Location l = new Location(x, y);
			m_canv.toLocal(l);
			mode_dessin = true;
			m_canv.x1 = l.x();
			m_canv.y1 = l.y();
		}

		@Override
		public void mouseReleased(int x, int y, int buttons) {
			m_clic = false;
			mode_dessin = false;
			Location l = new Location(x, y);
			m_canv.toLocal(l);
			m_canv.x2 = l.x();
			m_canv.y2 = l.y();
		}

		public void mouseEntered(int x, int y) {
			System.out.println("Curseur dans le canvas\n");
		}

		public void mouseExited() {
			System.out.println("Curseur hors du canvas\n");
		}
	}
}