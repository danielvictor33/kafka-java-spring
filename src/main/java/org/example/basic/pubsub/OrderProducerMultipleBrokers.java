package org.example.basic.pubsub;

import com.github.javafaker.Faker;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.PropsUtil;

public class OrderProducerMultipleBrokers {
    private static final String TOPIC = "kinaction_helloworld";
    private static final Faker FAKER = new Faker();

    public static void main(String[] args) {
        KafkaProducer<String, String> producer = new KafkaProducer<>(PropsUtil.getProducerWithMultipleBrokersProperties());

        try {
            for (int i = 0; i < 100; i++) {
                var nameCountry = FAKER.name().firstName() + "-" + FAKER.country().name();
                var randNum = FAKER.random().nextInt(1, 1000);
                producer.send(new ProducerRecord<>(TOPIC, nameCountry, randNum.toString()));
                System.out.println("sent record " + nameCountry + " integer " + randNum + " to topic ordertopic");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            producer.close();
        }
    }

}
