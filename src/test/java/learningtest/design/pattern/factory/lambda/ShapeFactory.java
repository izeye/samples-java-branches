package learningtest.design.pattern.factory.lambda;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import learningtest.design.pattern.factory.Circle;
import learningtest.design.pattern.factory.Rectangle;
import learningtest.design.pattern.factory.Shape;
import learningtest.design.pattern.factory.ShapeType;

/**
 * Factory for {@link Shape}.
 *
 * @author Johnny Lim
 */
public class ShapeFactory {

	private static final Map<ShapeType, Supplier<Shape>> SHAPE_BY_TYPE;
	static {
		Map<ShapeType, Supplier<Shape>> shapeByType = new HashMap<>();
		shapeByType.put(ShapeType.CIRCLE, Circle::new);
		shapeByType.put(ShapeType.RECTANGE, Rectangle::new);
		SHAPE_BY_TYPE = Collections.unmodifiableMap(shapeByType);
	}

	public Shape getShape(ShapeType shapeType) {
		Supplier<Shape> shapeSupplier = SHAPE_BY_TYPE.get(shapeType);
		if (shapeSupplier == null) {
			throw new IllegalArgumentException("Unexpected shape type: " + shapeType);
		}
		return shapeSupplier.get();
	}

}
