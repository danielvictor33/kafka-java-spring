package org.example.custserdes;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.PropsUtil;

public class OrderProducerCustSerdes {
    public static void main(String[] args) {

        KafkaProducer<String, Order> producer = new KafkaProducer<>(PropsUtil.getCustomProducerProperties());
        var order = Order.builder().customerName("Monica").product("eggs").quantity(12).build();
        ProducerRecord<String, Order> producerRecord = new ProducerRecord<>("OrderCSTopic", order.getCustomerName(), order);

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
