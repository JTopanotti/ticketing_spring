package io.ticketing.microservices.ticketing;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    protected TicketRepository ticketRepository;

    @Autowired(required=false)
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

    @GetMapping(value = "/tickets/{id}")
    public EntityModel<Ticket> getTicket(@PathVariable String id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
        return EntityModel.of(ticket,
                linkTo(methodOn(TicketController.class).getTicket(id)).withSelfRel(),
                linkTo(methodOn(TicketController.class).listTickets()).withRel("tickets"));
    }

    @PostMapping(value = "/tickets")
    public ResponseEntity<String> newTicket(@RequestBody TicketForm reqTicket) {
        Ticket ticket = new Ticket(reqTicket.getNumber());
        ticketRepository.insert(ticket);

        logger.info(ticket.toString());
        kafkaTemplate.send("tickets", "Test Message");

        logger.info("TicketRepository says system has "
                + ticketRepository.count() + " tickets");
        return ResponseEntity.ok().body("Ticket Created");
    }

    @GetMapping(value = "/tickets")
    public CollectionModel<EntityModel<Ticket>> listTickets() {
        List<EntityModel<Ticket>> tickets = ticketRepository.findAll().stream()
                .map(ticket -> EntityModel.of(ticket,
                        linkTo(methodOn(TicketController.class).getTicket(ticket.getId())).withSelfRel())
                ).collect(Collectors.toList());
        Link link = linkTo(methodOn(TicketController.class).listTickets()).withRel("tickets");
        return CollectionModel.of(tickets, link);
    }

    @DeleteMapping("/tickets/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        ticketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}