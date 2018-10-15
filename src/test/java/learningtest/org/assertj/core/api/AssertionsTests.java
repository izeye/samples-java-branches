package learningtest.org.assertj.core.api;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Tests for AssertJ assertions.
 *
 * @author Johnny Lim
 */
public class AssertionsTests {
	
	@Test
	public void test() {
		TolkienCharacter frodo = new TolkienCharacter();
		frodo.setName("Frodo");
		frodo.setRace(Race.HOBBIT);

		TolkienCharacter sam = new TolkienCharacter();
		sam.setName("Sam");
		sam.setAge(38);
		sam.setRace(Race.HOBBIT);
		
		TolkienCharacter boromir = new TolkienCharacter();
		boromir.setName("Boromir");
		boromir.setAge(37);
		boromir.setRace(Race.MAN);
		
		TolkienCharacter gandalf = new TolkienCharacter();
		gandalf.setName("Gandalf");
		
		TolkienCharacter legolas = new TolkienCharacter();
		legolas.setName("Legolas");
		legolas.setAge(1000);
		legolas.setRace(Race.ELF);
		
		TolkienCharacter pippin = new TolkienCharacter();
		pippin.setName("Pippin");
		pippin.setRace(Race.HOBBIT);
		
		TolkienCharacter merry = new TolkienCharacter();
		merry.setName("Merry");
		merry.setRace(Race.HOBBIT);
		
		TolkienCharacter aragorn = new TolkienCharacter();
		aragorn.setName("Aragorn");
		aragorn.setRace(Race.MAN);
		
		TolkienCharacter unknown = new TolkienCharacter();
		unknown.setName("");
		
		List<TolkienCharacter> fellowshipOfTheRing = new ArrayList<>();
		fellowshipOfTheRing.add(frodo);
		fellowshipOfTheRing.add(sam);
		fellowshipOfTheRing.add(boromir);
		fellowshipOfTheRing.add(legolas);
		fellowshipOfTheRing.add(gandalf);
		fellowshipOfTheRing.add(pippin);
		fellowshipOfTheRing.add(merry);
		fellowshipOfTheRing.add(aragorn);
		fellowshipOfTheRing.add(unknown);

		TolkienCharacter sauron = new TolkienCharacter();
		sauron.setName("Sauron");
		
		assertThat(frodo.getName()).isEqualTo("Frodo");
		assertThat(frodo).isNotEqualTo(sauron).isIn(fellowshipOfTheRing);
		
		assertThat(frodo.getName()).startsWith("Fro").endsWith("do").isEqualToIgnoringCase("frodo");
		
		assertThatThrownBy(() -> { throw new Exception("boom!"); })
				.isInstanceOf(Exception.class).hasMessageContaining("boom");
		
		Throwable thrown = catchThrowable(() -> { throw new Exception("boom!"); });
		assertThat(thrown).isInstanceOf(Exception.class).hasMessageContaining("boom");
		
		assertThat(fellowshipOfTheRing).extracting("name")
				.contains("Boromir", "Gandalf", "Frodo", "Legolas")
				.doesNotContain("Sauron", "Elrond");
		
		assertThat(fellowshipOfTheRing).extracting(TolkienCharacter::getName)
				.contains("Boromir", "Gandalf", "Frodo", "Legolas")
				.doesNotContain("Sauron", "Elrond");
		
		assertThat(fellowshipOfTheRing).extracting("name", "age", "race.name")
				.contains(tuple("Boromir", 37, "Man"),
						tuple("Sam", 38, "Hobbit"),
						tuple("Legolas", 1000, "Elf"));
		
		assertThat(fellowshipOfTheRing).filteredOn("race", Race.HOBBIT)
				.containsOnly(sam, frodo, pippin, merry);
		
		assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o"))
				.containsOnly(aragorn, frodo, legolas, boromir);

		assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o"))
				.containsOnly(aragorn, frodo, legolas, boromir)
				.extracting(character -> character.getRace().getName())
				.contains("Hobbit", "Elf", "Man");
	}

	@Test
	public void testAssertThatExceptionOfType() {
		assertThatExceptionOfType(IOException.class).isThrownBy(() -> { throw new IOException("boom!"); })
				.withMessage("%s!", "boom")
				.withMessageContaining("boom")
				.withNoCause();
	}
	
}
