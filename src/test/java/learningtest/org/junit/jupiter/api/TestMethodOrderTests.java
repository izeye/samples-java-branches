package learningtest.org.junit.jupiter.api;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tests for {@link TestMethodOrder}.
 *
 * @author Johnny Lim
 */
//@TestMethodOrder(MethodOrderer.Alphanumeric.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.Random.class)
class TestMethodOrderTests {

    @Test
    @Order(1)
    void cat() {
    }

    @Test
    @Order(2)
    void aardvark() {
    }

    @Test
    @Order(3)
    void bat() {
    }

}
