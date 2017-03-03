package learningtest.design.pattern.factory.lambda;

import learningtest.design.pattern.factory.Shape;
import learningtest.design.pattern.factory.ShapeType;
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
