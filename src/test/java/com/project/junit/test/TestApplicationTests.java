package com.project.junit.test;

import com.project.junit.test.Entity.Customer;
import com.project.junit.test.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @DisplayName("Add New Customer")
    @Disabled(value = "Disable test saveCustomer")
    public void saveCustomer() {
        Customer customer = new Customer();
        customer.setName("admin");
        customerRepository.save(customer);
        Assertions.assertEquals(2, customer.getId());
    }

    @Test
    @DisplayName("GetCustomerById")
    @Order(2)
    public void getCustomerById() {
        Optional<Customer> customer = customerRepository.findById(1);
        Customer customer1 = null;
        if (customer.isPresent()) {
            customer1 = customer.get();
        }
        System.out.println("Id: " + customer.get().getId());
        Assertions.assertNotNull(customer1);
        Assertions.assertEquals(1, customer1.getId());
    }

    @Test
    @DisplayName("Update Customer")
    @Order(1)
    public void updateCustomer() {
        Optional<Customer> customer = customerRepository.findById(1);
        if (customer.isPresent()) {
            customer.get().setName("P8admin");
            customerRepository.save(customer.get());
        }
        a();
        System.out.println("Name: " + customer.get().getName());
        Assertions.assertEquals("P8admin", customer.get().getName());
    }

    public static void a() {
        System.out.println("A");
    }

}
