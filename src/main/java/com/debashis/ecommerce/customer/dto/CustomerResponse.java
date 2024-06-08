package com.debashis.ecommerce.customer.dto;

import com.debashis.ecommerce.customer.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address) {

}
