package org.example.custserdes;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Order {
    private String customerName;
    private String product;
    private int quantity;

}
