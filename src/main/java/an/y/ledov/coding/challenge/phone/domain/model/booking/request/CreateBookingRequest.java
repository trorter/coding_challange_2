package an.y.ledov.coding.challenge.phone.domain.model.booking.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateBookingRequest extends BasicBookingRequest {

    private String personName;

}
