package learningtest.org.junit.jupiter.api;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Tests for {@link RepeatedTest}.
 *
 * @author Johnny Lim
 */
class RepeatedTestTests {

    @RepeatedTest(5)
    void repeatedTest(RepetitionInfo repetitionInfo) {
        assertThat(repetitionInfo.getTotalRepetitions()).isEqualTo(5);
    }

    @RepeatedTest(value = 5, name = "{currentRepetition} / {totalRepetitions}")
    void repeatedTestWithCustomName() {
    }

}
