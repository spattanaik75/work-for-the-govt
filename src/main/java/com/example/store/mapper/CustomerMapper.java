package com.example.store.mapper;

import com.example.store.dto.CustomerDTO;
import com.example.store.dto.CustomerOrderDTO;
import com.example.store.entity.Customer;
import com.example.store.entity.Order;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);

    List<CustomerDTO> customersToCustomerDTOs(List<Customer> customer);

    CustomerOrderDTO orderToCustomerOrderDTO(Order order);

    List<CustomerOrderDTO> ordersToCustomerOrderDTOs(List<Order> orders);
}
