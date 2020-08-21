package com.izeye.agent;

import java.lang.instrument.Instrumentation;

/**
 * Java agent for instrumentation.
 *
 * Copied from https://www.baeldung.com/java-size-of-object
 *
 * @author Johnny Lim
 */
public class InstrumentationAgent {

	private static volatile Instrumentation globalInstrumentation;

	public static void premain(String args, Instrumentation instrumentation) {
		globalInstrumentation = instrumentation;
	}

	public static long getObjectSize(Object object) {
		if (globalInstrumentation == null) {
			throw new IllegalStateException("Java agent hasn't been initialized.");
		}
		return globalInstrumentation.getObjectSize(object);
	}

}
