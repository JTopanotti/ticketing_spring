package io.ticketing.microservices.orders;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public OrderRepository orderRepository;

    @KafkaListener(topics = "tickets", groupId = "ticketing")
    public void newTicketListener(String message) {
        logger.info("-------RECEIVING MESSAGE:-------------");
        logger.info(message);
    }
}
