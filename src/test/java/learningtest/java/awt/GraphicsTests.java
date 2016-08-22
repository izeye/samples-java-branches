package learningtest.java.awt;

import java.awt.*;

import javax.swing.*;

/**
 * Tests for Graphics.
 *
 * @author Johnny Lim
 */
public class GraphicsTests {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new TestPanel());

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	private static class TestPanel extends JPanel {

		@Override
		public void paint(Graphics g) {
			super.paint(g);

			int[] xPoints = {25, 145, 25, 145, 25};
			int[] yPoints = {25, 25, 145, 145, 25};

			Polygon polygon = new Polygon(xPoints, yPoints, xPoints.length);
			System.out.println(polygon.contains(24, 24));
			System.out.println(polygon.contains(25, 25));
			System.out.println(polygon.contains(26, 26));

			g.drawPolygon(polygon);
		}

	}

}
