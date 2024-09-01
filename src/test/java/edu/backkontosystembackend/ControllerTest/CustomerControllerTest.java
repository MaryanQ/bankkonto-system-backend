package edu.backkontosystembackend.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.backkontosystembackend.controller.CustomerController;
import edu.backkontosystembackend.dto.CustomerDTO;
import edu.backkontosystembackend.service.CustomerService;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<CustomerDTO> customerList;

    @BeforeEach
    void setUp() {
        CustomerDTO customer1 = new CustomerDTO(1, "John Doe", "john@example.com", 1000.0);
        CustomerDTO customer2 = new CustomerDTO(2, "Jane Smith", "jane@example.com", 2000.0);
        CustomerDTO customer3 = new CustomerDTO(3, "Alice Johnson", "alice@example.com", 3000.0);

        customerList = Arrays.asList(customer1, customer2, customer3);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldFetchAllCustomers() throws Exception {
        given(customerService.getAllCustomers()).willReturn(customerList);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(customerList.size()))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldFetchOneCustomerById() throws Exception {
        long customerId = 1L;
        CustomerDTO customer = customerList.get(0);

        given(customerService.getCustomerById(customerId)).willReturn(customer);

        mockMvc.perform(get("/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(customer.getName()));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldCreateNewCustomer() throws Exception {
        CustomerDTO newCustomer = new CustomerDTO(4, "Bob Brown", "bob@example.com", 4000.0);

        given(customerService.createCustomer(any(CustomerDTO.class))).willReturn(newCustomer);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob Brown"));
    }


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldUpdateCustomer() throws Exception {
        long customerId = 1L;
        CustomerDTO updatedCustomer = new CustomerDTO(customerId, "Updated Name", "john@example.com", 1500.0);

        given(customerService.updateCustomer(any(CustomerDTO.class))).willReturn(updatedCustomer);

        mockMvc.perform(put("/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void shouldDeleteCustomer() throws Exception {
        long customerId = 1L;

        mockMvc.perform(delete("/customers/{id}", customerId))
                .andExpect(status().isNoContent());
    }
}
