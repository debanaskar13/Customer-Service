package com.debashis.ecommerce.customer;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

    @CreatedDate
    private LocalDateTime cteatedAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
