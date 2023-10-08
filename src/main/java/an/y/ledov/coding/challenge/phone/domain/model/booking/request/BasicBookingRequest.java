package an.y.ledov.coding.challenge.phone.domain.model.booking.request;

import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BasicBookingRequest {

    protected String entityId;

    protected EntityType entityType;

}
