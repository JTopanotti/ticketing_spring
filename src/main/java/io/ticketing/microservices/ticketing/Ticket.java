package io.ticketing.microservices.ticketing;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tickets")
public class Ticket {

    @Id
    private String id;

    private String number;

    public Ticket(String number) {
        super();
        this.id = new ObjectId().toString();
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format(
                "Ticket[id=%s, number='%s']",
                id, number);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
