package twolak.mssc.springframework.msscbrewery.services.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import twolak.mssc.springframework.msscbrewery.services.CustomerService;
import twolak.mssc.springframework.msscbrewery.web.model.CustomerDto;

/**
 *
 * @author twolak
 */
@ Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("First Customer")
                .build();
    }
}
