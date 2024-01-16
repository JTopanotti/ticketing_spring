package io.ticketing.microservices.ticketing;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    protected TicketRepository ticketRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static class TicketForm {
        private String number;
        public String getNumber() {
            return number;
        }
        public void setNumber(String number) {
            this.number = number;
        }
    }

    @RequestMapping(value = "/tickets", method = POST)
    public ResponseEntity<String> newTicket(@RequestBody TicketForm reqTicket) {
        Ticket ticket = new Ticket("0001", reqTicket.getNumber());
        ticketRepository.insert(ticket);

        logger.info(ticket.toString());
        kafkaTemplate.send("tickets", "Test Message");

        logger.info("TicketRepository says system has "
                + ticketRepository.count() + " tickets");
        return ResponseEntity.ok().body("Ticket Created");
    }
}