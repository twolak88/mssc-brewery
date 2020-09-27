package twolak.mssc.springframework.msscbrewery.services.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import twolak.mssc.springframework.msscbrewery.services.BeerService;
import twolak.mssc.springframework.msscbrewery.web.model.BeerDto;

/**
 *
 * @author twolak
 */
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
        //Todo
    }
}
