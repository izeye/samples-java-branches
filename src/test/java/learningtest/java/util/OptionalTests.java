package learningtest.java.util;

import java.util.Optional;

import lombok.Data;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Optional}.
 *
 * @author Johnny Lim
 */
public class OptionalTests {

	@Test
	public void test() {
		Computer computer = new Computer();
		SoundCard soundCard = new SoundCard();
		Usb usb = new Usb();
		String version = "3.0";
		usb.setVersion(version);
		soundCard.setUsb(Optional.ofNullable(usb));
		computer.setSoundCard(Optional.ofNullable(soundCard));
		Optional<Computer> maybeComputer = Optional.ofNullable(computer);
		String usbVersion = maybeComputer.flatMap(Computer::getSoundCard)
				.flatMap(SoundCard::getUsb)
				.map(Usb::getVersion)
				.orElse("UNKNOWN");
		assertThat(usbVersion).isEqualTo(version);
	}

	@Data
	private static class Computer {

		private Optional<SoundCard> soundCard;

	}

	@Data
	private static class SoundCard {

		private Optional<Usb> usb;

	}

	@Data
	private static class Usb {

		private String version;

	}

}
