package com.debashis.ecommerce.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.debashis.ecommerce.customer.dto.CustomerPageResponse;
import com.debashis.ecommerce.customer.dto.CustomerRequest;
import com.debashis.ecommerce.customer.dto.CustomerResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(this.service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {

        this.service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<CustomerPageResponse> getAllCustomer(
            @RequestParam(name = "sort_by", required = false, defaultValue = "id") String sortField,
            @RequestParam(name = "sort_direction", required = false, defaultValue = "ASC") String sorDirection,
            @RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "limit", required = false, defaultValue = "20") String limit

    ) {
        return ResponseEntity.ok(this.service.findAllCustomers(sortField, sorDirection, page, limit));
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(this.service.existsById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(this.service.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.accepted().build();
    }
}
