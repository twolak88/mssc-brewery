package twolak.springframework.msscbrewery.web.mappers;

import org.mapstruct.Mapper;
import twolak.springframework.msscbrewery.domain.Customer;
import twolak.springframework.msscbrewery.web.model.CustomerDto;

/**
 *
 * @author twolak
 */
@Mapper
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
