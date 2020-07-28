package learningtest.org.junit.jupiter.api;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link DisplayNameGeneration}.
 *
 * @author Johnny Lim
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DisplayNameGenerationTests {

    @Test
    void hello_world() {
        System.out.println("Hello, world!");
    }

}
