package learningtest.com.jayway.jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link JsonPath}.
 *
 * @author Johnny Lim
 */
public class JsonPathTests {

	@Test
	public void test() {
		String json = "{\"a1\":{\"b1\":{\"c1\":\"a1b1c1\"},\"b2\":[{\"c1\":\"a1b2c1\"},{\"c2\":\"a1b2c2\"},{\"c3\":\"a1b2c3\"}]}}";
		String a1b1c1 = JsonPath.parse(json).read("a1.b1.c1", String.class);
		assertThat(a1b1c1).isEqualTo("a1b1c1");
	}

}
