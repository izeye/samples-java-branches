package learningtest.org.elasticsearch.node;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by izeye on 15. 11. 18..
 */
public class NodeClientTests {

	Client client;
	
	@Before
	public void setUp() {
		Node node = nodeBuilder()
				.settings(ImmutableSettings.settingsBuilder().put("http.enabled", false))
				.client(true).node();
		this.client = node.client();
	}
	
	@After
	public void tearDown() {
		this.client.close();
	}
	
	@Test
	public void test() {
		SearchResponse response = this.client.prepareSearch("bank")
				.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			System.out.println(hit.getSource());
		}
	}
	
}
