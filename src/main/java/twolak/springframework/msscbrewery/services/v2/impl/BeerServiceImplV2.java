package twolak.springframework.msscbrewery.services.v2.impl;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twolak.springframework.msscbrewery.services.v2.BeerServiceV2;
import twolak.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import twolak.springframework.msscbrewery.web.model.v2.BeerStyleEnum;

/**
 *
 * @author twolak
 */
@Slf4j
@Service
public class BeerServiceImplV2 implements BeerServiceV2 {

    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .beerName("Warka")
                .beerStyle(BeerStyleEnum.ALE)
                .build();
    }

    @Override
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
        log.debug("Updating a beer");
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        log.debug("Deleting a beer");
    }
}
