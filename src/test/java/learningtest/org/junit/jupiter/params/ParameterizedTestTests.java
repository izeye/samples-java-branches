package learningtest.org.junit.jupiter.params;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

/**
 * Tests for {@link ParameterizedTest}.
 *
 * @author Johnny Lim
 */
class ParameterizedTestTests {

    @ParameterizedTest
    @ValueSource(strings = {
            "apple", "banana"
    })
    void valueSource(String value) {
        System.out.println(value);
    }

    @ParameterizedTest
    @MethodSource
    void methodSource(String value) {
        System.out.println(value);
    }

    static Stream<String> methodSource() {
        return Stream.of("apple", "banana");
    }

}
