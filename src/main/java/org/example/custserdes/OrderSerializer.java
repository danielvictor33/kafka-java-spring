package org.example.custserdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerializer implements Serializer<Order> {

    @Override
    public byte[] serialize(String topic, Order o) {
        return serialize(o);
    }

    private byte[] serialize(Order o) {
        var mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o).getBytes();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
