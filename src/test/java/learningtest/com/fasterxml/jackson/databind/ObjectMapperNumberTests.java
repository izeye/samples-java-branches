package learningtest.com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ObjectMapper} with numbers.
 *
 * @author Johnny Lim
 */
class ObjectMapperNumberTests {

	@Test
	@EnabledOnJre(JRE.JAVA_8)
	@SuppressWarnings("unchecked")
	void testJava8() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(
				"{\"favoriteNumbers\": [1, 2, " + Long.MAX_VALUE + "]}", Map.class);
		List<Long> favoriteNumbers = (List<Long>) map.get("favoriteNumbers");

		assertThat(favoriteNumbers.get(2)).isInstanceOf(Long.class);
		assertThatThrownBy(() -> favoriteNumbers.get(0))
				.isExactlyInstanceOf(ClassCastException.class)
				.hasMessageContaining("java.lang.Integer cannot be cast to java.lang.Long");
	}

	@Test
	@DisabledOnJre(JRE.JAVA_8)
	@SuppressWarnings("unchecked")
	void testJava11() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(
				"{\"favoriteNumbers\": [1, 2, " + Long.MAX_VALUE + "]}", Map.class);
		List<Long> favoriteNumbers = (List<Long>) map.get("favoriteNumbers");

		assertThat(favoriteNumbers.get(2)).isInstanceOf(Long.class);
		assertThatCode(() -> favoriteNumbers.get(0)).doesNotThrowAnyException();
		assertThatThrownBy(() -> favoriteNumbers.get(0).toString())
				.isExactlyInstanceOf(ClassCastException.class)
				.hasMessageContaining("class java.lang.Integer cannot be cast to class java.lang.Long");
	}

	@Test
	void testUseLongForInts() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
		Map<String, List<Long>> map = mapper.readValue(
				"{\"favoriteNumbers\": [1, 2, " + Long.MAX_VALUE + "]}",
				new TypeReference<Map<String, List<Long>>>() {});
		List<Long> favoriteNumbers = map.get("favoriteNumbers");

		assertThat(favoriteNumbers.get(0)).isInstanceOf(Long.class);
		assertThat(favoriteNumbers.get(1)).isInstanceOf(Long.class);
		assertThat(favoriteNumbers.get(2)).isInstanceOf(Long.class);
	}

}
