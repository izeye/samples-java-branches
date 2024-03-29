package learningtest.org.thymeleaf;

import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TemplateEngine}.
 *
 * @author Johnny Lim
 */
class TemplateEngineTests {

	private final TemplateEngine engine = createTemplateEngine();

	@Test
	void test() {
		assertThat(render("sample1", createPersonMap("Johnny", "Lim", 20)))
				.isEqualTo("This is the first sample.<p>My name is Johnny Lim.<p>I'm 20 years old.");
	}

	private Map<String, Object> createPersonMap(String firstName, String secondName, int age) {
		Map<String, Object> someMap = new HashMap<>();
		someMap.put("firstName", firstName);
		someMap.put("lastName", secondName);
		someMap.put("age", age);
		return someMap;
	}

	private String render(String templateName, Map<String, Object> personMap) {
		Context context = new Context();
		context.setVariable("person", personMap);
		return this.engine.process(templateName, context);
	}

	private TemplateEngine createTemplateEngine() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("/learningtest/thymeleaf/");
		resolver.setSuffix(".html");

		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);
		return engine;
	}

}
