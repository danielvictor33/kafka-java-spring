package org.example.schemaregistry;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.PropsUtil;
import org.example.avro.Order;

import java.time.Duration;
import java.util.Collections;

public class ConsumerWithSchemaRegistry {
    public static void main(String[] args) {
        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(PropsUtil.getCustomConsumerPropertiesWithAvroSchemaRegistry());
        consumer.subscribe(Collections.singletonList("OrderAvroTopic"));
        consumer.poll(Duration.ofSeconds(20))
                .forEach(record -> {
                    System.out.println("key:: " + record.key() + " value:: " + record.value() + " topic:: " + record.topic());
                });

        consumer.close();
    }
}
