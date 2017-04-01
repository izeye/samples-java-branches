package learningtest.java.util;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Arrays}.
 *
 * @author Johnny Lim
 */
public class ArraysTests {

    @Test
    public void testToString() {
        Object[] objects = {1, "Hello, world!"};
        System.out.println(objects);

        String prettyString = Arrays.toString(objects);
        assertThat(prettyString).isEqualTo("[1, Hello, world!]");
    }

}
