package learningtest.java.util;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Properties}.
 *
 * @author Johnny Lim
 */
public class PropertiesTests {

	@Test
	public void test() {
		Properties properties = new Properties();
		properties.setProperty("key1", "value1");
		properties.setProperty("key2", "value2");

		assertThat(properties.getProperty("key1")).isEqualTo("value1");
		assertThat(properties.getProperty("key2")).isEqualTo("value2");

		Set<Object> keys = new HashSet<>(properties.keySet());
		assertThat(keys).hasSize(2);
		assertThat(keys).contains("key1", "key2");

		properties.setProperty("key3", "value3");
		assertThat(properties.getProperty("key3")).isEqualTo("value3");

		properties.keySet().retainAll(keys);
		assertThat(properties.getProperty("key3")).isNull();
	}

}
