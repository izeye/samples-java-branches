package learningtest.org.graalvm.polyglot;

import org.apache.commons.io.IOUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for {@link PolyglotException}.
 *
 * @author Johnny Lim
 */
class PolyglotExceptionTests {

    private static final String LANGUAGE_ID = "js";

    @Test
    void test() throws URISyntaxException, IOException {
        Context context = Context.newBuilder(LANGUAGE_ID)
                .allowHostAccess(HostAccess.ALL)
                .build();

        Value bindings = context.getBindings(LANGUAGE_ID);
        bindings.putMember("SOME_SERVICE", new DefaultSomeService());

        Path path = Paths.get(getClass().getResource("/learningtest/js/test_polyglot_exception.js").toURI());
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            context.eval(LANGUAGE_ID, IOUtils.toString(reader));

            bindings.getMember("doServiceInJavaScript").execute();
            fail();
        }
        catch (PolyglotException ex) {
            Throwable throwable = ex.asHostException();
            assertThat(throwable).isInstanceOf(SomeServiceException.class)
                    .hasMessage("Failed to do this service.");
        }
    }

    interface SomeService {

        void doService();

    }

    public static class DefaultSomeService implements SomeService {

        @Override
        public void doService() {
            throw new SomeServiceException("Failed to do this service.");
        }

    }

    public static class SomeServiceException extends RuntimeException {

        public SomeServiceException(String message) {
            super(message);
        }

    }

}
