package com.izeye.test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

/**
 * Tests for {@link MemoryPoolMXBean} with {@code MaxMetaspaceSize} option.
 *
 * <ul>
 *     <li>Default: java -cp build/classes/java/main com.izeye.test.MemoryPoolMXBeanMaxMetaspaceSizeTests</li>
 *     <li>Default (debug): java -XX:+PrintFlagsFinal -cp build/classes/java/main com.izeye.test.MemoryPoolMXBeanMaxMetaspaceSizeTests | grep MaxMetaspaceSize</li>
 *     <li>MaxMetaspaceSize: java -XX:MaxMetaspaceSize=256m -cp build/classes/java/main com.izeye.test.MemoryPoolMXBeanMaxMetaspaceSizeTests</li>
 *     <li>MaxMetaspaceSize (debug): java -XX:MaxMetaspaceSize=256m -XX:+PrintFlagsFinal -cp build/classes/java/main com.izeye.test.MemoryPoolMXBeanMaxMetaspaceSizeTests | grep MaxMetaspaceSize</li>
 *     <li>JAVA_TOOL_OPTIONS: JAVA_TOOL_OPTIONS="-XX:MaxMetaspaceSize=256m" java -cp build/classes/java/main com.izeye.test.MemoryPoolMXBeanMaxMetaspaceSizeTests</li>
 *     <li>JAVA_TOOL_OPTIONS (debug): JAVA_TOOL_OPTIONS="-XX:MaxMetaspaceSize=256m" java -XX:+PrintFlagsFinal -cp build/classes/java/main com.izeye.test.MemoryPoolMXBeanMaxMetaspaceSizeTests | grep MaxMetaspaceSize</li>
 * </ul>
 *
 * @see <a href=https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8260349>JDK-8260349</a>
 *
 * @author Johnny Lim
 */
public class MemoryPoolMXBeanMaxMetaspaceSizeTests {

    public static void main(String[] args) {
        MemoryPoolMXBean memoryPoolMXBean = ManagementFactory.getPlatformMXBeans(MemoryPoolMXBean.class).stream()
                .filter((bean) -> bean.getName().equals("Metaspace"))
                .findFirst()
                .orElseThrow();
        System.out.println(memoryPoolMXBean.getUsage().getMax());
    }

}
