package twolak.mssc.springframework.msscbrewery.web.controller;

import java.net.InetAddress;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twolak.mssc.springframework.msscbrewery.services.BeerService;
import twolak.mssc.springframework.msscbrewery.web.model.BeerDto;

/**
 *
 * @author twolak
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(BeerController.BASE_URL)
public class BeerController {

    public static final String BASE_URL = "/api/v1/beer";
    private static final String BEER_ID = "beerId";
    
    private final BeerService beerService;
    @Value("${server.port}")
    private int port;
    
    @GetMapping("{" + BEER_ID + "}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable(BEER_ID) UUID beerId) {
        return new ResponseEntity<>(this.beerService.getBeerById(beerId), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity createNewBeer(@RequestBody BeerDto beerDto) {
        
        BeerDto savedBeerDto = this.beerService.saveNewBeer(beerDto);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        String location = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + port + BASE_URL + "/" + savedBeerDto.getId();
        httpHeaders.add(HttpHeaders.LOCATION, location);
        
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED); 
    }
}
