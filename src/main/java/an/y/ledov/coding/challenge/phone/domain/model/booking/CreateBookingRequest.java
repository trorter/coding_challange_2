package an.y.ledov.coding.challenge.phone.domain.model.booking;

import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBookingRequest {

    private String entityId;

    private EntityType entityType;

    private String personName;
}
