package com.debashis.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.debashis.ecommerce.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
