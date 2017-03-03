package learningtest.design.pattern.factory;

/**
 * Factory for {@link Shape}.
 *
 * @author Johnny Lim
 */
public class ShapeFactory {

	public Shape getShape(ShapeType shapeType) {
		switch (shapeType) {
			case CIRCLE:
				return new Circle();

			case RECTANGE:
				return new Rectangle();

			default:
				throw new IllegalArgumentException("Unexpected shape type: " + shapeType);
		}
	}

}
