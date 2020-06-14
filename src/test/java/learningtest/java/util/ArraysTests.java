package learningtest.java.util;

import java.util.Arrays;
import java.util.List;

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
        Object[] objects = { 1, "Hello, world!" };
        System.out.println(objects);

        String prettyString = Arrays.toString(objects);
        assertThat(prettyString).isEqualTo("[1, Hello, world!]");
    }

    @Test
    void asList() {
        String[] array = { "a", "b", "c", "d", "e", "f" };
        List<String> list = Arrays.asList(array);
        array[0] = "XXX";
        assertThat(list).containsExactly("XXX", "b", "c", "d", "e", "f");
        list.set(1, "YYY");
        assertThat(array).containsExactly("XXX", "YYY", "c", "d", "e", "f");
    }

}
