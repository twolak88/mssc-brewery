package twolak.mssc.springframework.msscbrewery.services.v2;

import java.util.UUID;
import twolak.mssc.springframework.msscbrewery.web.model.v2.BeerDtoV2;

/**
 *
 * @author twolak
 */
public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID beerId);
    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);
    void updateBeer(UUID beerId, BeerDtoV2 beerDto);
    void deleteBeerById(UUID beerId);
}
