package org.example.basic.pubsub;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.PropsUtil;

public class OrderProducer {
    public static void main(String[] args) {

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(PropsUtil.getProducerProperties());
        ProducerRecord<String, Integer> producerRecord = new ProducerRecord<>("OrderTopic", "Asus Zenbook", 10);

        try {
            producer.send(producerRecord);
            System.out.println("sent record to topic ordertopic");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            producer.close();
        }
    }
}
