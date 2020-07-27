package learningtest.org.junit.jupiter.params;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Tests for {@link ParameterizedTest}.
 *
 * @author Johnny Lim
 */
@Execution(ExecutionMode.CONCURRENT)
class ParameterizedTestFibonacciTests {

    private static long startTimeMillis;

    @BeforeAll
    static void beforeAll() {
        startTimeMillis = System.currentTimeMillis();
    }

    @AfterAll
    static void afterAll() {
        System.out.println(System.currentTimeMillis() - startTimeMillis);
    }


    @ParameterizedTest(name = "fib({0}) = {1}")
    @CsvSource({
            "0, 0",
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "40, 102334155",
            "41, 165580141",
            "42, 267914296",
            "43, 433494437",
            "44, 701408733",
            "45, 1134903170"
    })
    void fibonacci(int n, int expected) {
        System.out.println(Thread.currentThread() + " is working...");
        assertThat(fibonacci(n)).isEqualTo(expected);
    }

    int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacci(n - 2) + fibonacci(n - 1);
    }

}
