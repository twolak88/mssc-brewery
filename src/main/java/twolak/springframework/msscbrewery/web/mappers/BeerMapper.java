package twolak.springframework.msscbrewery.web.mappers;

import org.mapstruct.Mapper;
import twolak.springframework.msscbrewery.domain.Beer;
import twolak.springframework.msscbrewery.web.model.v2.BeerDtoV2;

/**
 *
 * @author twolak
 */
@Mapper
public interface BeerMapper {
    BeerDtoV2 beerToBeerDto(Beer beer);
    
    Beer beerToBeerDto(BeerDtoV2 beerDto);
}
