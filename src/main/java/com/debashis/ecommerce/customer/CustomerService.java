package com.debashis.ecommerce.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import com.debashis.ecommerce.customer.dto.CustomerPageResponse;
import com.debashis.ecommerce.customer.dto.CustomerRequest;
import com.debashis.ecommerce.customer.dto.CustomerResponse;
import com.debashis.ecommerce.customer.exception.CustomerNotFoundException;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {

        Customer savedCustomer = this.repository.save(mapper.toCustomer(request));
        return savedCustomer.getId();

    }

    public void updateCustomer(CustomerRequest request) {
        var customer = this.repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer : no customer found with id %s", request.id())));

        mergeCustomer(customer, request);

        this.repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public CustomerPageResponse findAllCustomers(String sortField, String sortDirection, String page, String limit) {

        Sort.Direction sDirection = sortDirection.toUpperCase().equals("ASC") ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        Sort sortBy = Sort.by(sDirection, sortField);

        int pageNumber, pageSize;
        try {
            pageNumber = Integer.parseInt(page);
            pageSize = Integer.parseInt(limit);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid request parameter");
        }

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortBy);
        Page<Customer> customerPage;
        try {
            customerPage = this.repository.findAll(pageRequest);
        } catch (PropertyReferenceException e) {
            log.info(e.getMessage());
            throw new IllegalArgumentException("Invalid request parameter");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException("Something went wrong");
        }
        return new CustomerPageResponse(
                customerPage.getTotalElements(),
                customerPage.getTotalPages(),
                customerPage.getNumber() + 1,
                customerPage.getNumberOfElements(),
                sortField,
                sDirection.toString(),
                customerPage.getContent().stream().map(mapper::fromCustomer).toList());

    }

    public boolean existsById(String customerId) {
        return this.repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return this.repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(
                        () -> new CustomerNotFoundException("No customer found with the provided id " + customerId));
    }

    public void deletById(String customerId) {
        this.repository.deleteById(customerId);
    }

}
