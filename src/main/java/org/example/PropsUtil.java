package org.example;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.example.custserdes.OrderDeserializer;
import org.example.custserdes.OrderSerializer;

import java.util.Properties;

public class PropsUtil {

    public static Properties getProducerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        return properties;
    }

    public static Properties getConsumerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put("group.id", "OrderGroup7");
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Set to earliest


        return properties;
    }

    public static Properties getCustomProducerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", OrderSerializer.class.getName());
        return properties;
    }

    public static Properties getCustomConsumerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", OrderDeserializer.class.getName());
        properties.put("group.id", "OrderCSGroup");
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Set to earliest


        return properties;
    }


    public static Properties getProducerPropertiesWithAvroSchemaRegistry() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", KafkaAvroSerializer.class.getName());
        properties.put("value.serializer", KafkaAvroSerializer.class.getName());
        properties.put("schema.registry.url", "http://localhost:8081/");
        return properties;
    }

    public static Properties getCustomConsumerPropertiesWithAvroSchemaRegistry() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", KafkaAvroDeserializer.class.getName());
        properties.put("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.put("schema.registry.url", "http://localhost:8081");
        properties.put("specific.avro.reader", "true");

        properties.put("group.id", "OrderSCGroup");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Set to earliest


        return properties;
    }
}
