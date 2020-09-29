package twolak.springframework.msscbrewery.web.mappers;

import org.mapstruct.Mapper;
import twolak.springframework.msscbrewery.domain.Beer;
import twolak.springframework.msscbrewery.web.model.BeerDto;

/**
 *
 * @author twolak
 */
@Mapper
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    
    Beer beerToBeerDto(BeerDto beerDto);
}
