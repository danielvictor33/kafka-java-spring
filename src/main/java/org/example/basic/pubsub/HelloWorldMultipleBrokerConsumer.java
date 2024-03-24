package org.example.basic.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Slf4j
public class HelloWorldMultipleBrokerConsumer {
    private static Boolean keepConsuming = true;


    public static void main(String[] args) {
        Properties kaProperties = new Properties();
        log.info("started");
        kaProperties.put("bootstrap.servers", "localhost:9092,localhost:9094,localhost:9095");
        kaProperties.put("group.id", "kinaction_helloconsumer");
        kaProperties.put("enable.auto.commit", "true");
        kaProperties.put("auto.commit.interval.ms", "1000");
        kaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kaProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Set to earliest


        HelloWorldMultipleBrokerConsumer helloWorldConsumer = new HelloWorldMultipleBrokerConsumer();
        helloWorldConsumer.consume(kaProperties);
        Runtime.getRuntime().addShutdownHook(new Thread(helloWorldConsumer::shutdown));
    }

    private void consume(Properties kaProperties) {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kaProperties)) {
            consumer.subscribe(List.of("kinaction_helloworld"));
            while (keepConsuming) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(250));
                for (ConsumerRecord<String, String> record : records) {
                    log.info("kinaction_info offset = {}, kinaction_value = {}, kinaction_key = {}", record.offset(), record.value(), record.key());
                }
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;
    }
}
