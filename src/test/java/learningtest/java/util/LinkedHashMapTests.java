package learningtest.java.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for {@link LinkedHashMap}.
 *
 * @author Johnny Lim
 */
public class LinkedHashMapTests {

	@Test
	public void test() {
		Map<String, Integer> map = new HashMap<>();
		putEntries(map);
		map.entrySet().forEach(System.out::println);
		map.values().forEach(System.out::println);

		map = new LinkedHashMap<>();
		putEntries(map);
		map.entrySet().forEach(System.out::println);
		map.values().forEach(System.out::println);
	}

	private void putEntries(Map<String, Integer> map) {
		map.put("key1", 1);
		map.put("key2", 2);
		map.put("key3", 3);
		map.put("key4", 4);
		map.put("key5", 5);
	}

}
