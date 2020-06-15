package learningtest.ru.lanwen.wiremock.ext;

import java.io.IOException;
import com.github.tomakehurst.wiremock.WireMockServer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.lanwen.wiremock.ext.WiremockResolver;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link WiremockResolver}.
 *
 * @author Johnny Lim
 */
@ExtendWith(WiremockResolver.class)
class WiremockResolverTests {

	@Test
	void test(@WiremockResolver.Wiremock WireMockServer server) throws IOException {
		server.stubFor(any(anyUrl()));
		OkHttpClient client = new OkHttpClient.Builder().build();
		Request request = new Request.Builder().url(server.baseUrl() + "/helloworld.txt").build();
		Response response = client.newCall(request).execute();
		assertThat(response.code()).isEqualTo(200);
	}

}
