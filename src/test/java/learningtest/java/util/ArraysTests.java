package learningtest.java.util;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Arrays}.
 *
 * @author Johnny Lim
 */
class ArraysTests {

    @Test
    void testToString() {
        Object[] objects = {1, "Hello, world!"};
        System.out.println(objects);

        String prettyString = Arrays.toString(objects);
        assertThat(prettyString).isEqualTo("[1, Hello, world!]");
    }

}
