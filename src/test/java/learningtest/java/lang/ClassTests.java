package learningtest.java.lang;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Class}.
 *
 * @author Johnny Lim
 */
public class ClassTests {

    @Test
    public void testGetAnnotation() {
        SomeAnnotation annotation = SomeAnnotationTests.class.getAnnotation(SomeAnnotation.class);
        assertThat(annotation.name()).isEqualTo("myAnnotation");
    }

    @Retention(RetentionPolicy.RUNTIME)
    private @interface SomeAnnotation {

        String name();

    }

    @SomeAnnotation(name = "myAnnotation")
    private static class SomeAnnotationTests {
    }

}
