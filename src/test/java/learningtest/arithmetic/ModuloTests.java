package learningtest.arithmetic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for modulo operator (%).
 *
 * @author Johnny Lim
 */
class ModuloTests {

	@Test
	void negative() {
		assertThat(-1 % 2).isEqualTo(-1);
		assertThat(-2 % 2).isEqualTo(0);
		assertThat(-3 % 2).isEqualTo(-1);
	}
	
}
