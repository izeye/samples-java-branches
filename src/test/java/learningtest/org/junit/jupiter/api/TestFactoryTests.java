package learningtest.org.junit.jupiter.api;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * Tests for {@link TestFactory}.
 *
 * @author Johnny Lim
 */
class TestFactoryTests {

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromIntStream() {
        return IntStream.iterate(0, (n) -> n + 2)
                .limit(10)
                .mapToObj(n -> dynamicTest("test" + n, () -> assertThat(n % 2).isEqualTo(0)));
    }

}
