package learningtest.gc;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

/**
 * Tests for {@link MemoryPoolMXBean}.
 *
 * @author Johnny Lim
 */
class MemoryPoolMXBeanTests {

    @Test
    void getName() {
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean memoryPoolMXBean : memoryPoolMXBeans) {
            System.out.println(memoryPoolMXBean.getType() + ": " + memoryPoolMXBean.getName());
        }
    }

}
