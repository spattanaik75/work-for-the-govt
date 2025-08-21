package com.example.store.controller;

import com.example.store.dto.CustomerDTO;
import com.example.store.entity.Customer;
import com.example.store.mapper.CustomerMapper;
import com.example.store.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @GetMapping
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers(@RequestParam(required = false) String name) {
        List<Customer> customers;
        if (name != null && !name.trim().isEmpty()) {
            customers = customerRepository.findByNameContainingIgnoreCase(name.trim());
        } else {
            customers = customerRepository.findAll();
        }
        return customerMapper.customersToCustomerDTOs(customers);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public CustomerDTO createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }
}
