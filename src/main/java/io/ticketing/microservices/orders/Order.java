package io.ticketing.microservices.orders;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders")
public class Order {
    @Id
    private String id;

    public Order(String id) {
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Ticket[id=%s]",
                id);
    }

}
