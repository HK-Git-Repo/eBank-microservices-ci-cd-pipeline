package kad.dev.customerservice;

import kad.dev.customerservice.entities.Customer;
import kad.dev.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {
				customerRepository.save(
						Customer.builder()
								.firstName("Hamza")
								.lastName("ELKADDARI")
								.email("kad@gmail.com")
								.build()
				);
			customerRepository.save(
					Customer.builder()
							.firstName("Amal")
							.lastName("benani")
							.email("amal@gmail.com")
							.build()
			);
			customerRepository.save(
					Customer.builder()
							.firstName("Mohammed")
							.lastName("imame")
							.email("med@gmail.com")
							.build()
			);
		};
	}
}
