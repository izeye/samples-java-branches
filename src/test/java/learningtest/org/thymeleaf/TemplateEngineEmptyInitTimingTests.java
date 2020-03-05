package learningtest.org.thymeleaf;

import java.util.HashMap;
import java.util.Map;

import com.izeye.util.TimingUtils;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateEngineEmptyInitTimingTests {

	private final TemplateEngine engine = createInitializedTemplateEngine("empty");

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
		return TimingUtils.printTiming(() -> engine.process(templateName, context));
	}

	private TemplateEngine createTemplateEngine() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("/learningtest/thymeleaf/");
		resolver.setSuffix(".html");

		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);
		return engine;
	}

	private TemplateEngine createInitializedTemplateEngine(String templateName) {
		TemplateEngine engine = createTemplateEngine();
		render(engine, templateName, null);
		return engine;
	}

}
