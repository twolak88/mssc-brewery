package twolak.mssc.springframework.msscbrewery.web.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private static final String BEER_ID_PATHVAR = "{" + BEER_ID + "}";
    
    private final BeerService beerService;
    
    @GetMapping(BEER_ID_PATHVAR)
    public ResponseEntity<BeerDto> getBeer(@PathVariable(BEER_ID) UUID beerId) {
        return new ResponseEntity<>(this.beerService.getBeerById(beerId), HttpStatus.OK);
    }
}
