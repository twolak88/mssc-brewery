package twolak.mssc.springframework.msscbrewery.services;

import java.util.UUID;
import twolak.mssc.springframework.msscbrewery.web.model.CustomerDto;

/**
 *
 * @author twolak
 */
public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}
