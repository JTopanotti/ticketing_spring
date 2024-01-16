package io.ticketing.microservices.ticketing;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.logging.Logger;

@Configuration
@ComponentScan
@EntityScan("io.ticketing.microservices.ticketing")
@EnableMongoRepositories
@PropertySource("classpath:db-config.properties")
public class TicketConfiguration {
    protected Logger logger;

    public TicketConfiguration() {
        logger = Logger.getLogger(getClass().getName());
    }

    /**
     * Creates an in-memory "rewards" database populated with test data for fast
     * testing
     */
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        logger.info("dataSource() invoked");
//
//        // Create an in-memory H2 relational database containing some demo
//        // accounts.
//        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
//                .addScript("classpath:testdb/data.sql").build();

//        logger.info("dataSource = " + dataSource);
//
//        // Sanity check
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        List<Map<String, Object>> accounts = jdbcTemplate.queryForList("SELECT number FROM T_ACCOUNT");
//        logger.info("System has " + accounts.size() + " accounts");
//
//        // Populate with random balances
//        Random rand = new Random();
//
//        for (Map<String, Object> item : accounts) {
//            String number = (String) item.get("number");
//            BigDecimal balance = new BigDecimal(rand.nextInt(10000000) / 100.0).setScale(2, RoundingMode.HALF_UP);
//            jdbcTemplate.update("UPDATE T_ACCOUNT SET balance = ? WHERE number = ?", balance, number);
//        }
//
//        return dataSource;
//    }
}
