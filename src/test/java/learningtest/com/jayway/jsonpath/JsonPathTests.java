package learningtest.com.jayway.jsonpath;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link JsonPath}.
 *
 * @author Johnny Lim
 */
public class JsonPathTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private final String json = "{\"a1\":{\"b1\":{\"c1\":\"a1b1c1\"},\"b2\":[{\"c1\":\"a1b2c1\"},{\"c2\":\"a1b2c2\"},{\"c3\":\"a1b2c3\"}]}}";

	@Test
	public void test() {
		String a1b1c1 = JsonPath.parse(this.json).read("a1.b1.c1", String.class);
		assertThat(a1b1c1).isEqualTo("a1b1c1");
	}

	@Test
	public void testMissingLeaf() {
		this.thrown.expect(PathNotFoundException.class);
		JsonPath.parse(this.json).read("a1.b1.c2", String.class);
	}

	@Test
	public void testMissingLeafWithDefaultPathLeafToNullOption() {
		Configuration conf = Configuration.defaultConfiguration().addOptions(
				Option.DEFAULT_PATH_LEAF_TO_NULL);
		String a1b1c2 = JsonPath.parse(this.json, conf).read("a1.b1.c2", String.class);
		assertThat(a1b1c2).isNull();
	}

	@Test
	public void testMissingIntermediate() {
		this.thrown.expect(PathNotFoundException.class);
		Configuration conf = Configuration.defaultConfiguration().addOptions(
				Option.DEFAULT_PATH_LEAF_TO_NULL);
		JsonPath.parse(this.json, conf).read("a2.b1.c1", String.class);
	}

}
