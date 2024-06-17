package com.debashis.ecommerce.dto;

import com.debashis.ecommerce.model.Address;

public record CustomerResponse(
                String id,
                String firstName,
                String lastName,
                String email,
                Address address) {

}
