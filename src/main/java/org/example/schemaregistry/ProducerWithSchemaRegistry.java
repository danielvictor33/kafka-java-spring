package org.example.schemaregistry;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.PropsUtil;
import org.example.avro.Order;

import java.util.UUID;

public class ProducerWithSchemaRegistry {
    public static void main(String[] args) {
        KafkaProducer<String, Order> producer = new KafkaProducer<>(PropsUtil.getProducerPropertiesWithAvroSchemaRegistry());
        ProducerRecord<String, Order> producerRecord = new ProducerRecord<>(
                "OrderAvroTopic", UUID.randomUUID().toString(), new Order("goop", "milk", "shiptohome", 10));

        try {
            producer.send(producerRecord);
            System.out.println("sent record to topic ordertopic with schema registry");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            producer.close();
        }
    }
}
