package io.ticketing.microservices.orders;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders")
public class Order {
    @Id
    private String id;

    public Order() {
        super();
        this.id = new ObjectId().toString();
    }

    @Override
    public String toString() {
        return String.format(
                "Ticket[id=%s]",
                id);
    }

}
