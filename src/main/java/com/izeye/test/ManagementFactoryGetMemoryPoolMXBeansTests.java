package com.izeye.test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

/**
 * Tests for {@link ManagementFactory#getMemoryPoolMXBeans()}
 *
 * @author Johnny Lim
 */
public class ManagementFactoryGetMemoryPoolMXBeansTests {

	public static void main(String[] args) {
		List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
		System.out.println(memoryPoolMXBeans);
	}

}
