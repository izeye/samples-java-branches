package learningtest.io.searchbox.client;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by izeye on 15. 11. 19..
 */
public class JestClientTests {

	JestClient client;
	
	@Before
	public void setUp() {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200").multiThreaded(true).build());
		this.client = factory.getObject();
	}
	
	@After
	public void tearDown() {
		this.client.shutdownClient();
	}
	
	@Test
	public void test() throws IOException {
		String query = "{ query: { match_all: {} } }";
		Search.Builder searchBuilder = new Search.Builder(query)
				.addIndex("bank").addType("account");
		SearchResult result = client.execute(searchBuilder.build());
		List<SearchResult.Hit<Account, Void>> hits = result.getHits(Account.class);
		for (SearchResult.Hit<Account, Void> hit : hits) {
			System.out.println(hit.source);
		}
	}
	
}
