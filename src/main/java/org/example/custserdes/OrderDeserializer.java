package org.example.custserdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class OrderDeserializer implements Deserializer<Order> {
    @Override
    public Order deserialize(String topic, byte[] bytes) {
        return deserialize(bytes);
    }

    private Order deserialize(byte[] bytes) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(bytes, Order.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
