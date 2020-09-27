package twolak.mssc.springframework.msscbrewery.services;

import java.util.UUID;
import twolak.mssc.springframework.msscbrewery.web.model.BeerDto;

/**
 *
 * @author twolak
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);
    BeerDto saveNewBeer(BeerDto beerDto);
    void updateBeer(UUID beerId, BeerDto beerDto);
}
