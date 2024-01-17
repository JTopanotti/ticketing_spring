package io.ticketing.microservices.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import io.ticketing.microservices.ticketing.TicketConfiguration;
import io.ticketing.microservices.ticketing.TicketRepository;

@SpringBootApplication
@EnableDiscoveryClient
@Import(TicketConfiguration.class)
public class TicketingServer {

    @Autowired
    protected TicketRepository ticketRepository;

    protected Logger logger = Logger.getLogger(TicketConfiguration.class.getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Default to registration server on localhost
        if (System.getProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME) == null)
            System.setProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME, "localhost");

        System.setProperty("spring.config.name", "ticketing-server");

        SpringApplication.run(TicketingServer.class, args);
    }

}
