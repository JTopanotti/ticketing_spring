package io.ticketing.microservices.ticketing;

class TicketNotFoundException extends RuntimeException {

    TicketNotFoundException(String id) {
        super("Could not find ticket " + id);
    }
}
