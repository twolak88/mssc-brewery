package twolak.springframework.msscbrewery.web.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author twolak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    
    @Null
    private UUID id;
    
    @NotBlank
    private String beerName;
    
    @NotBlank
    private String beerStyle;
    
    @Positive
    private Long upc;
    
    @Null
    private OffsetDateTime createdDate;
    
    @Null
    private OffsetDateTime lastUpdatedDate;
}
