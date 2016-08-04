package com.izeye.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Utilities for Strings.
 *
 * @author Johnny Lim
 */
public abstract class StringUtils {

	private StringUtils() {
	}

	public static <T> List<T> convert(List<String> strings, Function<String, T> f) {
		List<T> converted = new ArrayList<>();
		for (String string : strings) {
			converted.add(f.apply(string));
		}
		return converted;
	}

}
