package learningtest.org.assertj.core.api;

/**
 * Created by izeye on 16. 3. 8..
 */
public enum Race {
	
	HOBBIT("Hobbit"), MAN("Man"), ELF("Elf");
	
	private final String name;
	
	private Race(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
