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
    
}