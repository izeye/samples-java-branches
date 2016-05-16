package learningtest.org.apache.kafka.clients.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 * Created by izeye on 16. 5. 16..
 */
public class KafkaConsumerTests {
	
	@Test
	public void test() {
		Properties properties = new Properties();
		properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(GROUP_ID_CONFIG, "test");
		properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		Consumer<String, String> consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Arrays.asList("my-topic"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("Offset = %d, key = %s, value = %s%n",
						record.offset(), record.key(), record.value());
			}
		}
	}
	
}
