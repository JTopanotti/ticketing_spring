package io.ticketing.microservices.ticketing;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tickets")
public class Ticket {

    @Id
    private String id;

    private String number;

    public Ticket(String id, String number) {
        super();
        this.id = id;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format(
                "Ticket[id=%s, number='%s']",
                id, number);
    }
}
