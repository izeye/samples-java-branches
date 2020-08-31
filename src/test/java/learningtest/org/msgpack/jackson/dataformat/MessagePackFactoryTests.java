package learningtest.org.msgpack.jackson.dataformat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link MessagePackFactory}.
 *
 * @author Johnny Lim
 */
class MessagePackFactoryTests {

	@Test
	void test() throws IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("firstName", "Johnny");
		map.put("lastName", "Lim");
		map.put("age", 20);

		ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());
		byte[] bytes = objectMapper.writeValueAsBytes(map);
		Map<String, Object> readMap = objectMapper.readValue(bytes, new TypeReference<>() {
		});
		assertThat(readMap).isEqualTo(map);
	}

}
