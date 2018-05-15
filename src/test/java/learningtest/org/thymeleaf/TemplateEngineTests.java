package learningtest.org.thymeleaf;

import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineTests {

	@Test
	public void test() {
		// Note the additional "</p>".
		String expected = "My name is Johnny Lim.<p></p>";

		TemplateEngine engine = getTemplateEngine();

		Map<String, Object> someMap = new HashMap<>();
		someMap.put("firstName", "Johnny");
		someMap.put("lastName", "Lim");
		someMap.put("age", 20);

		Context context = new Context();
		context.setVariable("someMap", someMap);

		assertThat(engine.process("sample", context)).isEqualTo(expected);
	}

	private TemplateEngine getTemplateEngine() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("learningtest/thymeleaf/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("LEGACYHTML5");

		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);
		return engine;
	}

}
