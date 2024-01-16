package io.ticketing.microservices.orders;

import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan
@EntityScan("io.ticketing.microservices.orders")
@EnableMongoRepositories
@PropertySource("classpath:db-config.properties")
public class OrderConfiguration {
    protected Logger logger;

    public OrderConfiguration() {
        this.logger = Logger.getLogger(this.getClass().getName());
    }
}
