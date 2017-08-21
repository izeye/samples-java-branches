package learningtest.org.elasticsearch.client.transport;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Ignore;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests for {@link TransportClient}.
 *
 * @author Johnny Lim
 */
public class TransportClientTests {
	
	@Ignore
	@Test
	public void test() throws UnknownHostException {
		Map<String, String> map = new HashMap<>();
		map.put("firstName", "Johnny");
		map.put("lastName", "Lim");
		
		TransportClient client = new PreBuiltTransportClient(settings());
		client.addTransportAddress(
				new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		IndexResponse indexResponse = client.prepareIndex("logstash", "logstash").setSource(map).get();
		System.out.println(indexResponse);
	}

	private Settings settings() {
		return Settings.builder().put("cluster.name", "elasticsearch").build();
	}

}
