package twolak.springframework.msscbrewery.web.controller;

import twolak.springframework.msscbrewery.web.controller.CustomerController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import twolak.springframework.msscbrewery.services.CustomerService;
import twolak.springframework.msscbrewery.web.model.CustomerDto;

/**
 *
 * @author twolak
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = {CustomerController.class})
public class CustomerControllerTest {
    
    @MockBean
    private CustomerService customerService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private CustomerDto validCustomerDto;
    
    @BeforeEach
    public void setUp() {
        this.validCustomerDto = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Customer")
                .build();
    }

    @Test
    public void testGetCustomer() throws Exception {
        BDDMockito.given(this.customerService.getCustomerById(ArgumentMatchers.any(UUID.class))).willReturn(validCustomerDto);
        
        this.mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.BASE_URL + "/" + this.validCustomerDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(this.validCustomerDto.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(this.validCustomerDto.getName())));
        BDDMockito.then(this.customerService).should(Mockito.times(1)).getCustomerById(ArgumentMatchers.any(UUID.class));
        BDDMockito.then(this.customerService).shouldHaveNoMoreInteractions();
    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        CustomerDto customerDto = this.validCustomerDto;
        customerDto.setId(null);
        CustomerDto savedCustomerDto = CustomerDto.builder().id(UUID.randomUUID()).name("new customer").build();
        
        String customerJson = this.objectMapper.writeValueAsString(customerDto);
        
        BDDMockito.given(this.customerService.createNewCustomer(ArgumentMatchers.any(CustomerDto.class))).willReturn(savedCustomerDto);
        
        this.mockMvc.perform(MockMvcRequestBuilders.post(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        String customerJson = this.objectMapper.writeValueAsString(this.validCustomerDto);

        this.mockMvc.perform(MockMvcRequestBuilders.put(CustomerController.BASE_URL + "/" + this.validCustomerDto.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        BDDMockito.then(this.customerService).should(Mockito.times(1)).updateCustomer(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(CustomerDto.class));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(CustomerController.BASE_URL + "/" + this.validCustomerDto.getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        BDDMockito.then(this.customerService).should(Mockito.times(1)).deleteCustomerById(ArgumentMatchers.any(UUID.class));
    }
    
}
