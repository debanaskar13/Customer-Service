package com.debashis.ecommerce.customer.dto;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors) {

}
