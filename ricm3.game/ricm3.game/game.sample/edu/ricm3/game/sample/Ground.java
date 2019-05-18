package edu.ricm3.game.sample;

import java.awt.Color;
import java.awt.Graphics;

public class Ground {

	Model m_model;
	Color m_color = Color.green;
	int m_x, m_y, m_width, m_height;

	Ground(Model model, int x, int y, int width, int height) {

		m_model = model;
		m_color = Color.green;
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
	}

	/**
	 * paints this square on the screen.
	 * 
	 * @param g
	 **/

	void paint(Graphics g) {
		g.setColor(m_color);
		g.fillRect(m_x, m_y, m_width, m_height);
	}

}
