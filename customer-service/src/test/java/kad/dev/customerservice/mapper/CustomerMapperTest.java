package kad.dev.customerservice.mapper;

import kad.dev.customerservice.dto.CustomerDTO;
import kad.dev.customerservice.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private CustomerMapper underTest = new CustomerMapper();

    @Test
    void fromCustomer() {
        Customer customer = Customer.builder()
                .id(1L)
                .firstName("hamza")
                .lastName("elkaddari")
                .email("kad@gmail.com")
                .build();
        CustomerDTO target = CustomerDTO.builder()
                .id(1L)
                .firstName("hamza")
                .lastName("elkaddari")
                .email("kad@gmail.com")
                .build();
        CustomerDTO result = underTest.fromCustomer(customer);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(target);
    }

    @Test
    void fromCustomerDTO() {
        Customer target = Customer.builder()
                .id(1L)
                .firstName("hamza")
                .lastName("elkaddari")
                .email("kad@gmail.com")
                .build();
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(1L)
                .firstName("hamza")
                .lastName("elkaddari")
                .email("kad@gmail.com")
                .build();
        Customer result = underTest.fromCustomerDTO(customerDTO);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(target);
    }

    @Test
    void fromListCustomers() {
        List<Customer> customers = List.of(
                Customer.builder()
                        .id(1L)
                        .firstName("hamza")
                        .lastName("elkaddari")
                        .email("kad@gmail.com")
                        .build(),
                Customer.builder()
                        .id(1L)
                        .firstName("hamza2")
                        .lastName("elkaddari2")
                        .email("kad2@gmail.com")
                        .build()
        );
        List<CustomerDTO> targets = List.of(
                CustomerDTO.builder()
                        .id(1L)
                        .firstName("hamza")
                        .lastName("elkaddari")
                        .email("kad@gmail.com")
                        .build(),
                CustomerDTO.builder()
                        .id(1L)
                        .firstName("hamza2")
                        .lastName("elkaddari2")
                        .email("kad2@gmail.com")
                        .build()
        );
        List<CustomerDTO> result = underTest.fromListCustomers(customers);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(targets);
    }

    @Test
    void fromListCustomersDTO() {
        List<CustomerDTO> customersDTO = List.of(
                CustomerDTO.builder()
                        .id(1L)
                        .firstName("hamza")
                        .lastName("elkaddari")
                        .email("kad@gmail.com")
                        .build(),
                CustomerDTO.builder()
                        .id(1L)
                        .firstName("hamza2")
                        .lastName("elkaddari2")
                        .email("kad2@gmail.com")
                        .build()
        );
        List<Customer> targets = List.of(
                Customer.builder()
                        .id(1L)
                        .firstName("hamza")
                        .lastName("elkaddari")
                        .email("kad@gmail.com")
                        .build(),
                Customer.builder()
                        .id(1L)
                        .firstName("hamza2")
                        .lastName("elkaddari2")
                        .email("kad2@gmail.com")
                        .build()
        );
        List<Customer> result = underTest.fromListCustomersDTO(customersDTO);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(targets);
    }

    @Test
    void fromNullCustomer() {
        Customer customer = null;
        AssertionsForClassTypes.assertThatThrownBy(()-> underTest.fromCustomer(customer)).isInstanceOf(IllegalArgumentException.class);
    }
}