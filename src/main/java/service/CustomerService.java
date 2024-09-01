package service;

import dto.CustomerDTO;
import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //konverter en Customer till en CustomerDTO
    public CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getBalance());
    }

    // Konverter en CustomerDTO til en Customer
    public Customer convertToEntity(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getBalance());
    }

    // hent all kunder
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    // hent en kunde ved id
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return convertToDTO(customer);
        } else {
            return null;
        }
    }

    //opret en kunde
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        //step 1: convert CustomerDTO to CustomerEntity
        Customer customer = convertToEntity(customerDTO);

        //step 2: save the customer entity to database
        customer = customerRepository.save(customer);

        //step 3: Convert the saved Customer Entity back to CustomerDTO
        return convertToDTO(customer);
    }

    // Opdater en kunde
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        // Step 1: Hent den eksisterende kunde fra databasen
        return customerRepository.findById(customerDTO.getId())
                .map(existingCustomer -> {
                    // Step 2: Opdater felterne på den eksisterende Customer-entitet
                    existingCustomer.setName(customerDTO.getName());
                    existingCustomer.setEmail(customerDTO.getEmail());
                    existingCustomer.setBalance(customerDTO.getBalance());

                    // Step 3: Gem den opdaterede Customer-entitet tilbage i databasen
                    Customer updatedCustomer = customerRepository.save(existingCustomer);

                    // Step 4: Konverter den opdaterede Customer-entitet tilbage til CustomerDTO
                    return convertToDTO(updatedCustomer);
                })
                .orElse(null);
    }

        //slet en kunde
        public void deleteCustomer(long id) {
            // Step 1: Find kunden
            Customer existingCustomer = customerRepository.findById(id).orElse(null);

            // Step 2: Hvis kunden eksisterer, slet den
            if (existingCustomer != null) {
                customerRepository.delete(existingCustomer);
            } else {
                throw new RuntimeException("Customer not found with id: " + id);
            }
        }


    }
