package learningtest.supertypetoken.lambda.hashliteral;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for hash literals.
 *
 * @author Johnny Lim
 */
class HashLiteralTests {

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testHash() {
        Map<String, String> hash = hash(
                hello -> "world",
                bob -> bob,
                bill -> "was here"
        );

        assertThat(hash.get("hello")).isEqualTo("world");
        assertThat(hash.get("bob")).isEqualTo("bob");
        assertThat(hash.get("bill")).isEqualTo("was here");
    }

    private static <T> Map<String, T> hash(NamedValue<T>... nameValuePairs) {
        Map<String, T> map = new HashMap<>();
        Arrays.stream(nameValuePairs)
                .forEach(pair -> map.put(pair.name(), pair.value()));
        return map;
    }

}
