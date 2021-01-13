package learningtest.javax.script;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
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

    private static Stream<String> getEngineNames() {
        return Stream.of("nashorn", "graal.js");
    }

}
