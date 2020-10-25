package learningtest.org.junit.jupiter.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class TimeoutTests {

    @BeforeEach
    @Timeout(1)
    void beforeEach() throws InterruptedException {
//        Thread.sleep(2000);
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void test() throws InterruptedException {
//        Thread.sleep(200);
    }

}
