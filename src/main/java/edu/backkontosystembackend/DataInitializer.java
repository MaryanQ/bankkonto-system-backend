package edu.backkontosystembackend;

import edu.backkontosystembackend.dto.CustomerDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import edu.backkontosystembackend.service.CustomerService;

@Component
public class DataInitializer implements CommandLineRunner {


    private final CustomerService customerService;

    public DataInitializer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerService.getAllCustomers().isEmpty()) {
            System.out.println("No customers found, initializing data...");

            customerService.createCustomer(new CustomerDTO(0, "John Doe", "john@example.com", 1000.0));
            customerService.createCustomer(new CustomerDTO(0, "Jane Smith", "jane@example.com", 2000.0));
            customerService.createCustomer(new CustomerDTO(0, "Alice Johnson", "alice@example.com", 3000.0));

            System.out.println("Data initialization complete.");
        } else {
            System.out.println("Customers already exist, no initialization needed.");
        }
    }
}