package com.izeye.test;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Tests for signals.
 *
 * @author Johnny Lim
 */
public class SignalTests {

	public static void main(String[] args) throws InterruptedException {
		SignalHandler handler = signal -> System.out.println(signal.getName() + ": " + signal.getNumber());
		Signal.handle(new Signal("INT"), handler);
		Signal.handle(new Signal("TERM"), handler);
		Thread.sleep(Long.MAX_VALUE);
	}

}
