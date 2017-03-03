package learningtest.design.pattern.factory;

import org.junit.Test;

/**
 * Tests for {@link ShapeFactory}.
 *
 * @author Johnny Lim
 */
public class ShapeFactoryTests {

	@Test
	public void testGetShape() {
		ShapeFactory shapeFactory = new ShapeFactory();

		Shape circle = shapeFactory.getShape(ShapeType.CIRCLE);
		circle.draw();

		Shape rectangle = shapeFactory.getShape(ShapeType.RECTANGE);
		rectangle.draw();
	}

}
