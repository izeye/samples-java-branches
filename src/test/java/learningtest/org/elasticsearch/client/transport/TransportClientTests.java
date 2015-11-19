package learningtest.org.elasticsearch.client.transport;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by izeye on 15. 11. 18..
 */
public class TransportClientTests {

	Client client;

	@Before
	public void setUp() throws UnknownHostException {
		this.client = TransportClient.builder().build()
				.addTransportAddress(new InetSocketTransportAddress(
						InetAddress.getByName("localhost"), 9300));
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
