package edu.backkontosystembackend.serviceTest;

import edu.backkontosystembackend.dto.CustomerDTO;
import edu.backkontosystembackend.repository.CustomerRepository;
import edu.backkontosystembackend.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//integration test
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll(); // Ryd databasen før hver test
        CustomerDTO customer1 = new CustomerDTO(0, "John Doe", "john@example.com", 1000.0);
        CustomerDTO customer2 = new CustomerDTO(0, "Jane Smith", "jane@example.com", 2000.0);

        customerService.createCustomer(customer1);
        customerService.createCustomer(customer2);
    }

    @Test
    void testFindAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        assertThat(customers).hasSize(2);
    }

    @Test
    void testFindCustomerById() {
        CustomerDTO customer = customerService.getAllCustomers().get(0);
        CustomerDTO foundCustomer = customerService.getCustomerById(customer.getId());
        assertNotNull(foundCustomer);
        assertThat(foundCustomer.getName()).isEqualTo(customer.getName());
    }

    @Test
    void testCreateCustomer() {
        CustomerDTO customer = new CustomerDTO(0, "Alice Johnson", "alice@example.com", 3000.0);
        CustomerDTO savedCustomer = customerService.createCustomer(customer);
        assertNotNull(savedCustomer);
        assertThat(savedCustomer.getId()).isNotZero();
    }

    @Test
    void testUpdateCustomer() {
        CustomerDTO customer = customerService.getAllCustomers().get(0);
        customer.setName("Updated Name");
        CustomerDTO updatedCustomer = customerService.updateCustomer(customer);

        assertNotNull(updatedCustomer);
        assertThat(updatedCustomer.getName()).isEqualTo("Updated Name");
    }

    @Test
    void testDeleteCustomer() {
        CustomerDTO customer = customerService.getAllCustomers().get(0);
        customerService.deleteCustomer(customer.getId());

        List<CustomerDTO> customers = customerService.getAllCustomers();
        assertThat(customers).hasSize(1);
    }
}