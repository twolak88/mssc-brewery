package twolak.mssc.springframework.msscbrewery.services.impl;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twolak.mssc.springframework.msscbrewery.services.CustomerService;
import twolak.mssc.springframework.msscbrewery.web.model.CustomerDto;

/**
 *
 * @author twolak
 */
@Slf4j
@ Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("First Customer")
                .build();
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(customerDto.getName())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.debug("Updating a customer");
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        log.debug("Updating a customer");
    }
}
