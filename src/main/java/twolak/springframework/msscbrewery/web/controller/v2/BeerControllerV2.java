package twolak.springframework.msscbrewery.web.controller.v2;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import twolak.springframework.msscbrewery.services.v2.BeerServiceV2;
import twolak.springframework.msscbrewery.web.model.v2.BeerDtoV2;

/**
 *
 * @author twolak
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(BeerControllerV2.BASE_URL)
public class BeerControllerV2 {
    public static final String BASE_URL = "/api/v2/beer";
    
    private static final String BEER_ID = "beerId";

    private final BeerServiceV2 beerService;
    @Value("${server.port}")
    private int port;

    @GetMapping("{" + BEER_ID + "}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable(BEER_ID) UUID beerId) {
        return new ResponseEntity<>(this.beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createNewBeer(@Valid @RequestBody BeerDtoV2 beerDto) {

        BeerDtoV2 savedBeerDto = this.beerService.saveNewBeer(beerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        String location = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + port + BASE_URL + "/" + savedBeerDto.getId();
        httpHeaders.add(HttpHeaders.LOCATION, location);

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{" + BEER_ID + "}")
    public void updateBeer(@PathVariable(BEER_ID) UUID beerId, @Valid @RequestBody BeerDtoV2 beerDto) {
        this.beerService.updateBeer(beerId, beerDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{" + BEER_ID + "}")
    public void deleteBeer(@PathVariable(BEER_ID) UUID beerId) {
        this.beerService.deleteBeerById(beerId);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException constraintViolationException) {
        List<String> errors = new ArrayList<>(constraintViolationException.getConstraintViolations().size());
        constraintViolationException.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
