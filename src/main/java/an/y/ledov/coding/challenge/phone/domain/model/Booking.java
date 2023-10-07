package an.y.ledov.coding.challenge.phone.domain.model;

import an.y.ledov.coding.challenge.phone.rest.dto.RestEntityType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Booking {

    private String id;

    private String entityId;

    private RestEntityType entityType;

    private LocalDateTime startDate;

    private String personName;

}
