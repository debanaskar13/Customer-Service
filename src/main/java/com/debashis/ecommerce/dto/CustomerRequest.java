package com.debashis.ecommerce.dto;

import com.debashis.ecommerce.model.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,

        @NotNull(message = "Customer First Name is Required") String firstName,

        @NotNull(message = "Customer Last Name is Required") String lastName,

        @NotNull(message = "Customer Email is Required") @Email(message = "Customer Email is not a valid Email Address") String email,

        Address address) {

}
