package io.ticketing.microservices.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import io.ticketing.microservices.orders.OrderConfiguration;
import io.ticketing.microservices.orders.OrderRepository;

@SpringBootApplication
@EnableDiscoveryClient
@Import(OrderConfiguration.class)
public class OrdersServer {

    @Autowired
    protected OrderRepository orderRepository;

    protected Logger logger = Logger.getLogger(OrdersServer.class.getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Default to registration server on localhost
        if (System.getProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME) == null)
            System.setProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME, "localhost");

        System.setProperty("spring.config.name", "orders-server");

        SpringApplication.run(OrdersServer.class, args);
    }

}
