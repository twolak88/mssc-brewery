package twolak.springframework.msscbrewery.services.impl;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twolak.springframework.msscbrewery.services.BeerService;
import twolak.springframework.msscbrewery.web.model.BeerDto;

/**
 *
 * @author twolak
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Warka")
                .beerStyle("Warka Style")
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        log.debug("Updating a beer");
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        log.debug("Deleting a beer");
    }
}
