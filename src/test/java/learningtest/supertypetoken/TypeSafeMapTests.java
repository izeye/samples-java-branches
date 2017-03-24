package learningtest.supertypetoken;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for type-safe map.
 *
 * @author Johnny Lim
 */
public class TypeSafeMapTests {

    @Test
    public void test() {
        TypeSafeMap map = new TypeSafeMap();
        map.setValue(String.class, "Java");
        map.setValue(Integer.class, 0xcafebabe);

        String string = map.getValue(String.class);
        assertThat(string).isEqualTo("Java");

        Integer integer = map.getValue(Integer.class);
        assertThat(integer).isEqualTo(0xcafebabe);
    }

    public static class TypeSafeMap {

        private Map<Class<?>, Object> valueByClass = new HashMap<>();

        public <T> void setValue(Class<T> clazz, T value) {
            this.valueByClass.put(clazz, value);
        }

        public <T> T getValue(Class<T> clazz) {
            return clazz.cast(valueByClass.get(clazz));
        }

    }

}
