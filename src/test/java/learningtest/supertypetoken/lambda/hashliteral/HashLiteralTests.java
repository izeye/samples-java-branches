package learningtest.supertypetoken.lambda.hashliteral;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for hash literals.
 *
 * @author Johnny Lim
 */
public class HashLiteralTests {

    @Test
    public void testHash() {
        Map<String, String> hash = hash(
                hello -> "world",
                bob -> bob,
                bill -> "was here"
        );

        assertThat(hash.get("hello")).isEqualTo("world");
        assertThat(hash.get("bob")).isEqualTo("bob");
        assertThat(hash.get("bill")).isEqualTo("was here");
    }

    public static <T> Map<String, T> hash(NamedValue<T>... nameValuePairs) {
        Map<String, T> map = new HashMap<>();
        Arrays.stream(nameValuePairs)
                .forEach(pair -> map.put(pair.name(), pair.value()));
        return map;
    }

}
