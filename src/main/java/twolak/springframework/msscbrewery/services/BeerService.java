package twolak.springframework.msscbrewery.services;

import java.util.UUID;
import twolak.springframework.msscbrewery.web.model.BeerDto;

/**
 *
 * @author twolak
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);
    BeerDto saveNewBeer(BeerDto beerDto);
    void updateBeer(UUID beerId, BeerDto beerDto);
    void deleteBeerById(UUID beerId);
}
