package com.izeye.agent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Tests for {@link InstrumentationAgent}.
 *
 * @author Johnny Lim
 */
public class InstrumentationAgentTests {

	private static void printObjectSize(Object object) {
		System.out.println("Object type: " + object.getClass()
				+ ", size: " + InstrumentationAgent.getObjectSize(object) + " bytes");
	}

	public static void main(String[] args) {
		printObjectSize("");
		printObjectSize("Hello, world!");
		printObjectSize(new String[] { "a", "b", "c", "d", "e" });
		printObjectSize(new String[100]);
		printObjectSize(new ArrayList<String>());
		printObjectSize(new StringBuilder(100));
		printObjectSize(0);
		printObjectSize(0L);
		printObjectSize(0.0);
		printObjectSize(true);
		printObjectSize(Fruit.APPLE);
		printObjectSize(new Object());
		printObjectSize(new EmptyClass());
		printObjectSize(new StringClass());
		printObjectSize(new TwoStringsClass());
		printObjectSize(new AtomicLong());
	}

	private enum Fruit {
		APPLE
	}

	private static class EmptyClass {
	}

	private static class StringClass {
		private String string;
	}

	private static class TwoStringsClass {
		private String string1;
		private String string2;
	}

}
