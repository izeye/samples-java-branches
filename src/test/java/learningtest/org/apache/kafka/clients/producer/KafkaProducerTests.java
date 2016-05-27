package learningtest.org.apache.kafka.clients.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * Created by izeye on 16. 5. 15..
 */
public class KafkaProducerTests {
	
	@Ignore
	@Test
	public void test() {
		Properties properties = new Properties();
		properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		Producer<String, String> producer = new KafkaProducer<>(properties);
		for (int i = 0; i < 100; i++) {
			producer.send(new ProducerRecord<>(
					"my-topic", Integer.toString(i), Integer.toString(i)));
		}
		
		// NOTE: Should sleep because `send()` is asynchronous.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
