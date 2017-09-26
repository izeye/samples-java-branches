package learningtest.com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObjectMapper} with numbers.
 *
 * @author Johnny Lim
 */
public class ObjectMapperNumberTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(
				"{\"favoriteNumbers\": [1, 2, " + Long.MAX_VALUE + "]}", Map.class);
		List<Long> favoriteNumbers = (List<Long>) map.get("favoriteNumbers");

		assertThat(favoriteNumbers.get(2)).isInstanceOf(Long.class);

		this.thrown.expect(ClassCastException.class);
		this.thrown.expectMessage("java.lang.Integer cannot be cast to java.lang.Long");
		Long favoriteNumber = favoriteNumbers.get(0);

		// Unreachable.
		System.out.println(favoriteNumber);
	}

	@Test
	public void testUseLongForInts() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
		Map<String, Object> map = mapper.readValue(
				"{\"favoriteNumbers\": [1, 2, " + Long.MAX_VALUE + "]}", Map.class);
		List<Long> favoriteNumbers = (List<Long>) map.get("favoriteNumbers");

		assertThat(favoriteNumbers.get(0)).isInstanceOf(Long.class);
		assertThat(favoriteNumbers.get(1)).isInstanceOf(Long.class);
		assertThat(favoriteNumbers.get(2)).isInstanceOf(Long.class);
	}

}
