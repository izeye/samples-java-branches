package learningtest.org.objenesis;

import lombok.Data;
import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObjenesisStd}.
 *
 * @author Johnny Lim
 */
public class ObjenesisStdTests {

	@Test
	public void test() {
		Objenesis objenesis = new ObjenesisStd();

		MyThingy thingy1 = objenesis.newInstance(MyThingy.class);

		thingy1.setId(1L);
		validateInstance(thingy1, 1L);

		ObjectInstantiator<MyThingy> thingyInstantiator = objenesis.getInstantiatorOf(MyThingy.class);

		MyThingy thingy2 = thingyInstantiator.newInstance();
		thingy2.setId(1L);
		MyThingy thingy3 = thingyInstantiator.newInstance();
		thingy3.setId(1L);
		MyThingy thingy4 = thingyInstantiator.newInstance();
		thingy4.setId(1L);
		validateInstance(thingy2, 1L);
		validateInstance(thingy3, 1L);
		validateInstance(thingy4, 1L);

		assertThat(thingy1).isEqualTo(thingy2);
		assertThat(thingy2).isEqualTo(thingy3);
		assertThat(thingy3).isEqualTo(thingy4);

		assertThat(thingy1).isNotSameAs(thingy2);
		assertThat(thingy2).isNotSameAs(thingy3);
		assertThat(thingy3).isNotSameAs(thingy4);
	}

	private void validateInstance(MyThingy thingy, Long expectedId) {
		assertThat(thingy).isInstanceOf(MyThingy.class);
		assertThat(thingy.getId()).isEqualTo(expectedId);
	}

	@Data
	private static class MyThingy {

		private Long id;

	}

}
