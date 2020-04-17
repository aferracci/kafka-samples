package com.ferry.kafka.samples;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaConsumerSubscribeApp {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id", "test");
		
		KafkaConsumer<String, String> myConsumer = new KafkaConsumer<String, String>(props);
		
		ArrayList<String> topics = new ArrayList<String>();
		topics.add("my-big-topic");
		//topics.add("my-other-topic");
		
		myConsumer.subscribe(topics);
		
		try {
			
			while (true) {
				ConsumerRecords<String, String> records = myConsumer.poll(10);
				for (ConsumerRecord<String, String> record: records) {
					
					// Process the retrieved records
					System.out.println(
							String.format("Topic: %s, Partition: %d, Offset: %d key: %s, Value: %s", 
									record.topic(), record.partition(), record.offset(), record.key(), record.value()));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			myConsumer.close();
		}
	}

}
