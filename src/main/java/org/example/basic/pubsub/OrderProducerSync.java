package org.example.basic.pubsub;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.PropsUtil;


public class OrderProducerSync {
    public static void main(String[] args) {

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(PropsUtil.getProducerProperties());
        ProducerRecord<String, Integer> producerRecord = new ProducerRecord<>("OrderTopic", "Asus Zenbook", 10);

        try {
            RecordMetadata recordMetadata = producer.send(producerRecord).get();
            System.out.println("sent record to topic ordertopic");
            System.out.println(recordMetadata.partition() + " " + recordMetadata.offset() + " " + recordMetadata.timestamp());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            producer.close();
        }
    }
}
