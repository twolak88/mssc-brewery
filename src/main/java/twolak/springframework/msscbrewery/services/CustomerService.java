package twolak.springframework.msscbrewery.services;

import java.util.UUID;
import twolak.springframework.msscbrewery.web.model.CustomerDto;

/**
 *
 * @author twolak
 */
public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
    CustomerDto createNewCustomer(CustomerDto customerDto);
    void updateCustomer(UUID customerId, CustomerDto customerDto);
    void deleteCustomerById(UUID customerId);
}
