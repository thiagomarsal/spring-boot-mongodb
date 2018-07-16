package com.example;

import com.example.domain.Customer;
import com.example.domain.Domain;
import com.example.domain.Payload;
import com.example.domain.TTLExpiration;
import com.example.repository.CustomerRepository;
import com.example.repository.DomainRepository;
import com.example.repository.PayloadRepository;
import com.example.repository.TTLExpirationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private TTLExpirationRepository ttlExpirationRepository;

    @Autowired
    private PayloadRepository payloadRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        cleanup();
        saveAll();

        // fetch all customers
        for (Customer customer : customerRepository.findAll()) {
            LOGGER.info("Customers found with findAll(): {}", customer);
        }
        LOGGER.info("-------------------------------");

        // fetch an individual customer
        final Customer john = customerRepository.findByFirstName("John");
        LOGGER.info("Customer found with findByFirstName('John'): {}", john);
        LOGGER.info("--------------------------------");

        for (Customer customer : customerRepository.findByLastName("Smith")) {
            LOGGER.info("Customers found with findByLastName('Smith'): {}", customer);
        }
        LOGGER.info("--------------------------------");

        // fetch an individual domain
        Domain myDomain = domainRepository.findFirstByDomain("MyDomain");
        LOGGER.info("Domain found with findFirstByDomain(MyDomain): {}", myDomain);
        LOGGER.info("-------------------------------");

        // fetch an individual expiration
        TTLExpiration ttlExpiration = ttlExpirationRepository.findFirstByValue("MyValue");
        LOGGER.info("TTLExpiration found with findFirstByValue(MyValue): {}", ttlExpiration);
        LOGGER.info("-------------------------------");

        // fetch all expiration
        for (TTLExpiration expiration : ttlExpirationRepository.findAll()) {
            LOGGER.info("TTLExpirations found with findAll(): {}", expiration);
        }
        LOGGER.info("-------------------------------");

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        // fetch all expiration
        for (TTLExpiration expiration : ttlExpirationRepository.findAll()) {
            LOGGER.info("TTLExpirations found with findAll(): {}", expiration);
        }
        LOGGER.info("-------------------------------");
    }

    private void saveAll() {
        // save a couple of customers
        customerRepository.save(new Customer("John", "Smith"));
        customerRepository.save(new Customer("Thiago", "Farias"));

        // save a couple of domains
        domainRepository.save(new Domain("MyDomain"));
        domainRepository.save(new Domain("sample"));

        // save a couple of expiration
        ttlExpirationRepository.save(new TTLExpiration("MyValue"));
        ttlExpirationRepository.save(new TTLExpiration("Example"));

        // save a payload
        payloadRepository.save(new Payload("This is a payload", new Date(), Boolean.FALSE, 0, 5, 1L, TimeUnit.HOURS, new Customer("John", "Smith")));
    }

    private void cleanup() {
        customerRepository.deleteAll();
        domainRepository.deleteAll();
        ttlExpirationRepository.deleteAll();
        payloadRepository.deleteAll();
    }
}
