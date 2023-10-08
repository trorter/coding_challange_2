package an.y.ledov.coding.challenge.phone.mapping;

import an.y.ledov.coding.challenge.phone.adapter.persistence.model.Bookings;
import an.y.ledov.coding.challenge.phone.domain.model.booking.Booking;
import an.y.ledov.coding.challenge.phone.rest.dto.booking.RestBooking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BookingMapper {

    RestBooking toRestBooking(Booking booking);

    Booking toBooking(Bookings booking);
}
