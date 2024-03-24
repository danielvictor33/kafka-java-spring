package org.example.basic.pubsub;

import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements org.apache.kafka.clients.producer.Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("published to order-topic asynchronously");
        System.out.println(recordMetadata.partition() + " " + recordMetadata.offset() + " " + recordMetadata.timestamp());
        if (e != null) {
            e.printStackTrace();
        }
    }
}
