package com.ferry.kafka.samples;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerApp {
	
	public static void main(String[] args) {
		
		// Creating producer config settings
		Properties props = new Properties();
		props.put("bootstrap.servers", "127.0.0.1:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		Producer<String, String> myProducer = new KafkaProducer<String, String>(props);
		
		try {
			
			for (int i = 0; i < 150 ;i++) {
				myProducer.send(new ProducerRecord<String, String>("my-big-topic", Integer.toString(i), "MyMessage: " + Integer.toString(i)));
			}
			
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {
			myProducer.close();
		}
	}

}
