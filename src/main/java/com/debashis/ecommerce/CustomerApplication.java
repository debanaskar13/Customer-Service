package com.debashis.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.debashis.ecommerce.model.Address;
import com.debashis.ecommerce.model.Customer;
import com.debashis.ecommerce.repository.CustomerRepository;

import net.datafaker.Faker;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoAuditing
public class CustomerApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	private final Faker faker = new Faker();

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	public void createFakeCustomers() throws Exception {
		for (int i = 0; i < 200; i++) {
			Customer customer = new Customer();
			customer.setFirstName(faker.name().firstName());
			customer.setLastName(faker.name().lastName());
			customer.setEmail(faker.internet().emailAddress());
			customer.setAddress(new Address(
					faker.address().streetAddress(),
					faker.address().buildingNumber(),
					faker.address().zipCode()));
			customerRepository.save(customer);
		}

		System.out.println("200 customer documents inserted successfully!");
	}

	@Override
	public void run(String... args) throws Exception {
		// try {
		// createFakeCustomers();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
