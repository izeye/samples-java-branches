package learningtest.supertypetoken;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for super-type token.
 *
 * @author Johnny Lim
 */
public class SuperTypeTokenTests {

    @Test
    public void test() throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        TypeReference<ArrayList<String>> typeReference1 = new TypeReference<ArrayList<String>>() {
        };
        assertThat(typeReference1.getType().toString())
                .isEqualTo("java.util.ArrayList<java.lang.String>");

        List<String> strings = typeReference1.newInstance();
        strings.add("string1");
        String string = strings.get(0);
        assertThat(string).isInstanceOf(String.class);
        assertThat(string).isEqualTo("string1");

        TypeReference<ArrayList> typeReference2 = new TypeReference<ArrayList>() {
        };
        assertThat(typeReference2.getType().toString()).isEqualTo("class java.util.ArrayList");

        List objects = typeReference2.newInstance();
        objects.add(1);
        Object object = objects.get(0);
        assertThat(object).isInstanceOf(Integer.class);
        assertThat(object).isEqualTo(1);
    }

    public static abstract class TypeReference<T> {

        private final Type type;
        private volatile Constructor<?> constructor;

        protected TypeReference() {
            Type superClass = getClass().getGenericSuperclass();
            if (superClass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        }

        public Type getType() {
            return this.type;
        }

        public T newInstance() throws NoSuchMethodException, IllegalAccessException,
                InvocationTargetException, InstantiationException {
            if (this.constructor == null) {
                Class<?> rawType =
                        (Class<?>) ((this.type instanceof Class<?>) ?
                                this.type : ((ParameterizedType) this.type).getRawType());
                this.constructor = rawType.getConstructor();
            }
            return (T) this.constructor.newInstance();
        }

    }

}
