package learningtest.javax.script;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tests for {@link ScriptEngine}.
 *
 * @author Johnny Lim
 */
class ScriptEngineTests {

    private static final String NASHORN_COMPATIBILITY_PROPERTY_NAME = "polyglot.js.nashorn-compat";

    private static String nashornCompatibilityPropertyValue;

    @BeforeAll
    static void beforeAll() {
        nashornCompatibilityPropertyValue = System.getProperty(NASHORN_COMPATIBILITY_PROPERTY_NAME);

        System.setProperty(NASHORN_COMPATIBILITY_PROPERTY_NAME, "true");
    }

    @AfterAll
    static void afterAll() {
        if (nashornCompatibilityPropertyValue == null) {
            System.clearProperty(NASHORN_COMPATIBILITY_PROPERTY_NAME);
        }
        else {
            System.setProperty(NASHORN_COMPATIBILITY_PROPERTY_NAME, nashornCompatibilityPropertyValue);
        }
    }

    @ParameterizedTest
    @MethodSource("getEngineNames")
    void basic(String engineByName) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(engineByName);
        engine.eval("print('Hello, world!');");
    }

    @ParameterizedTest
    @MethodSource("getEngineNames")
    void externalJavaScriptFunction(String engineName) throws URISyntaxException, IOException, ScriptException, NoSuchMethodException {
        Path path = Paths.get(ScriptEngineTests.class.getResource("/learningtest/js/greet.js").toURI());
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(engineName);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            engine.eval(reader);

            Invocable invocable = (Invocable) engine;
            String message = (String) invocable.invokeFunction("greet", "Johnny");
            assertThat(message).isEqualTo("Hello, Johnny!");
        }
    }

    @ParameterizedTest
    @MethodSource("getEngineNames")
    void javaTypeInJavaScript(String engineName) throws URISyntaxException, IOException, ScriptException, NoSuchMethodException {
        Path path = Paths.get(ScriptEngineTests.class.getResource("/learningtest/js/java_type.js").toURI());
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(engineName);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            engine.eval(reader);

            Invocable invocable = (Invocable) engine;
            BigDecimal converted = (BigDecimal) invocable.invokeFunction("toBigDecimal", 1);
            assertThat(converted).isEqualTo(BigDecimal.ONE);
        }
    }

    private static Stream<String> getEngineNames() {
        return Stream.of("nashorn", "graal.js");
    }

}
