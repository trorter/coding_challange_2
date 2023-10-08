package an.y.ledov.coding.challenge.phone.domain.model.booking.result;

import an.y.ledov.coding.challenge.phone.domain.model.booking.Booking;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateBookingResult extends BasicBookingResult {

    private Booking booking;

}
