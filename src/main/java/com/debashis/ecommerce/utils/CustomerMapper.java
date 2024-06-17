package com.debashis.ecommerce.utils;

import org.springframework.stereotype.Service;

import com.debashis.ecommerce.dto.CustomerRequest;
import com.debashis.ecommerce.dto.CustomerResponse;
import com.debashis.ecommerce.model.Customer;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(),
                customer.getEmail(), customer.getAddress());
    }

}
