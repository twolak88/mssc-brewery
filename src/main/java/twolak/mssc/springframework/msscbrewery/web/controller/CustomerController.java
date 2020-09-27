package twolak.mssc.springframework.msscbrewery.web.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import twolak.mssc.springframework.msscbrewery.services.CustomerService;
import twolak.mssc.springframework.msscbrewery.web.model.CustomerDto;

/**
 *
 * @author twolak
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    
    public static final String BASE_URL = "/api/v1/customer";
    private static final String CUSTOMER_ID = "customerId";
    private final CustomerService customerService;
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{" + CUSTOMER_ID + "}")
    public CustomerDto getCustomer(@PathVariable(CUSTOMER_ID) UUID customerId) {
        return this.customerService.getCustomerById(customerId);
    }
}
