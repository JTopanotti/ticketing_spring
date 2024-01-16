package io.ticketing.microservices.ticketing;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    @Query("{number:'?0'}")
    public List<Ticket> findByNumber(String number);

    public long count();
}
