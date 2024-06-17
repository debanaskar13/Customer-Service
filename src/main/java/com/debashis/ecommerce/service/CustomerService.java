package com.debashis.ecommerce.service;

import com.debashis.ecommerce.dto.CustomerPageResponse;
import com.debashis.ecommerce.dto.CustomerRequest;
import com.debashis.ecommerce.dto.CustomerResponse;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    CustomerPageResponse findAllCustomers(String sortField, String sortDirection, String page, String limit);

    boolean existsById(String customerId);

    CustomerResponse findById(String customerId);

    void deletById(String customerId);

}
