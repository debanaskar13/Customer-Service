package com.debashis.ecommerce.dto;

import java.util.Map;

public record ErrorResponse(
                Map<String, String> errors) {

}
