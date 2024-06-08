package com.debashis.ecommerce.customer;

import org.springframework.stereotype.Service;

import com.debashis.ecommerce.customer.dto.CustomerRequest;
import com.debashis.ecommerce.customer.dto.CustomerResponse;

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
