package learningtest.org.thymeleaf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineDummyInitTimingTests {

	private final TemplateEngine engine = createInitializedTemplateEngine("dummy", null);

	@Test
	void test() {
		assertThat(render(this.engine, "sample1", createPersonMap("Johnny", "Lim", 20)))
				.isEqualTo("This is the first sample.<p>My name is Johnny Lim.<p>I'm 20 years old.");

		assertThat(render(this.engine, "sample2", createPersonMap("John", "Kim", 21)))
				.isEqualTo("This is the second sample.<p>My name is John Kim.<p>I'm 21 years old.");
	}

	private Map<String, Object> createPersonMap(String firstName, String secondName, int age) {
		Map<String, Object> someMap = new HashMap<>();
		someMap.put("firstName", firstName);
		someMap.put("lastName", secondName);
		someMap.put("age", age);
		return someMap;
	}

	private String render(TemplateEngine engine, String templateName, Map<String, Object> personMap) {
		Context context = new Context();
		context.setVariable("person", personMap);
		return time(() -> engine.process(templateName, context));
	}

	private TemplateEngine createTemplateEngine() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("/learningtest/thymeleaf/");
		resolver.setSuffix(".html");

		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);
		return engine;
	}

	private TemplateEngine createInitializedTemplateEngine(String templateName, Map<String, Object> personMap) {
		TemplateEngine engine = createTemplateEngine();
		render(engine, templateName, personMap);
		return engine;
	}

	private <T> T time(Supplier<T> supplier) {
		long startTimeMillis = System.currentTimeMillis();
		try {
			return supplier.get();
		}
		finally {
			long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
			System.out.println("Elapsed time: " + elapsedTimeMillis);
		}
	}

}
