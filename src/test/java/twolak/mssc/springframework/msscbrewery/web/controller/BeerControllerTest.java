package twolak.mssc.springframework.msscbrewery.web.controller;

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
import twolak.mssc.springframework.msscbrewery.services.BeerService;
import twolak.mssc.springframework.msscbrewery.web.model.BeerDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author twolak
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = {BeerController.class})
public class BeerControllerTest {
    
    @MockBean
    private BeerService beerService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private BeerDto validBeerDto;
    
    @BeforeEach
    public void setUp() {
        this.validBeerDto = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Debowe")
                .beerStyle("DStyle")
                .upc(1L)
                .build();
    }

    @Test
    public void testGetBeer() throws Exception {
        BDDMockito.given(this.beerService.getBeerById(ArgumentMatchers.any(UUID.class))).willReturn(validBeerDto);
        
        this.mockMvc.perform(get(BeerController.BASE_URL + "/" + this.validBeerDto.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(this.validBeerDto.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beerName", CoreMatchers.is(this.validBeerDto.getBeerName())));
    }

    @Test
    public void testCreateNewBeer() throws Exception {
        BeerDto beerDto = validBeerDto;
        beerDto.setId(null);
        BeerDto savedBeerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("new beer").build();
        
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        
        BDDMockito.given(this.beerService.saveNewBeer(ArgumentMatchers.any(BeerDto.class))).willReturn(savedBeerDto);
        
        this.mockMvc.perform(MockMvcRequestBuilders.post(BeerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdateBeer() throws Exception {
        String beerDtoJson = objectMapper.writeValueAsString(validBeerDto);
        
        this.mockMvc.perform(MockMvcRequestBuilders.put(BeerController.BASE_URL + "/" + validBeerDto.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        BDDMockito.then(this.beerService).should(Mockito.times(1)).updateBeer(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(BeerDto.class));
    }
    
}