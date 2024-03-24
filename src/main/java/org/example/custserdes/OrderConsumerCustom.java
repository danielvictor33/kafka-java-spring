package org.example.custserdes;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.PropsUtil;

import java.time.Duration;
import java.util.Collections;

public class OrderConsumerCustom {
    public static void main(String[] args) {
        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(PropsUtil.getCustomConsumerProperties());
        consumer.subscribe(Collections.singletonList("OrderCSTopic"));

        consumer.poll(Duration.ofSeconds(20))
                .forEach(record -> {
                    System.out.println(record.key() + " " + record.value() + " " + record.topic());
                });

        consumer.close();
    }
}
