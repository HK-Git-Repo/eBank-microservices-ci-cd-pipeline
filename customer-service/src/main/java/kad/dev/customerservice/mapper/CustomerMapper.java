package kad.dev.customerservice.mapper;

import kad.dev.customerservice.dto.CustomerDTO;
import kad.dev.customerservice.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {
    private ModelMapper underClass = new ModelMapper();

    public CustomerDTO fromCustomer(Customer customer) {
        return underClass.map(customer, CustomerDTO.class);
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        return underClass.map(customerDTO, Customer.class);
    }

    public List<CustomerDTO> fromListCustomers(List<Customer> customers) {
        return customers.stream().map(c-> underClass.map(c, CustomerDTO.class)).collect(Collectors.toList());
    }

    public List<Customer> fromListCustomersDTO(List<CustomerDTO> customersDTO) {
        return customersDTO.stream().map(c-> underClass.map(c, Customer.class)).collect(Collectors.toList());
    }
}
