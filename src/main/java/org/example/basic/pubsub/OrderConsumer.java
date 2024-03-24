package org.example.basic.pubsub;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.PropsUtil;

import java.time.Duration;
import java.util.Collections;

public class OrderConsumer {
    public static void main(String[] args) {
        KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(PropsUtil.getConsumerProperties());
        consumer.subscribe(Collections.singletonList("OrderTopic"));

        consumer.poll(Duration.ofSeconds(20))
                .forEach(record -> {
                    System.out.println(record.key() + " " + record.value() + " " + record.topic());
                });


        consumer.close();
    }
}
