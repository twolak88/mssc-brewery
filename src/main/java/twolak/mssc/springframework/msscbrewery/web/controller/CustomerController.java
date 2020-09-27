package twolak.mssc.springframework.msscbrewery.web.controller;

import java.net.InetAddress;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Value("${server.port}")
    private int port;
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{" + CUSTOMER_ID + "}")
    public CustomerDto getCustomer(@PathVariable(CUSTOMER_ID) UUID customerId) {
        return this.customerService.getCustomerById(customerId);
    }
    
    @PostMapping
    public ResponseEntity createNewCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = this.customerService.createNewCustomer(customerDto);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        String location = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + port + BASE_URL + "/" + savedCustomerDto.getId();
        httpHeaders.add(HttpHeaders.LOCATION, location);
        
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{" + CUSTOMER_ID + "}")
    public void updateCustomer(@PathVariable(CUSTOMER_ID) UUID customerId, @RequestBody CustomerDto customerDto){
        this.customerService.updateCustomer(customerId, customerDto);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{" + CUSTOMER_ID + "}")
    public void deleteCustomer(@PathVariable(CUSTOMER_ID) UUID customerId) {
        this.customerService.deleteCustomerById(customerId);
    }
}
