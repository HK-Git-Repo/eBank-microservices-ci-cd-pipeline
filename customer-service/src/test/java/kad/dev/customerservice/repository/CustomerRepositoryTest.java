package kad.dev.customerservice.repository;

import kad.dev.customerservice.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
//@Testcontainers // Unit Test using Postgres inside Docker container
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Unit Test using Postgres inside Docker container
class CustomerRepositoryTest {
    /*@Container
    @ServiceConnection
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16");
*/
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    private void setUp() {
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
    }

    /*@Test
    void connectionEstablishedTest() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }*/

    @Test
    void shouldFindByEmail() {
        String email = "kad@gmail.com";
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        assertThat(byEmail).isPresent();
    }

    @Test
    void shouldNotFindByEmail() {
        String email = "xxxx@xxxx@@gmail.com";
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        assertThat(byEmail).isEmpty();
    }

    @Test
    void shouldFindCustomersByFirstName() {
        String keyword = "h";
        List<Customer> target = List.of(
                Customer.builder()
                        .firstName("Hamza")
                        .lastName("ELKADDARI")
                        .email("kad@gmail.com")
                        .build(),
                        Customer.builder()
                                .firstName("Mohammed")
                                .lastName("imame")
                                .email("med@gmail.com")
                                .build()
        );
        List<Customer> myList = customerRepository.findByFirstNameContainsIgnoreCase(keyword);
        assertThat(myList).isNotNull();
        assertThat(myList.size()).isEqualTo(target.size());
        assertThat(myList).usingRecursiveComparison().ignoringFields("id").isEqualTo(target);
    }
}