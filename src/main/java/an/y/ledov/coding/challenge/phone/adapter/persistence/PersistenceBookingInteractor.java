package an.y.ledov.coding.challenge.phone.adapter.persistence;

import an.y.ledov.coding.challenge.phone.domain.model.Booking;
import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import an.y.ledov.coding.challenge.phone.mapping.BookingMapper;
import an.y.ledov.coding.challenge.phone.mapping.EntityTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersistenceBookingInteractor {

    public final BookingRepository bookingRepository;

    public final EntityTypeMapper entityTypeMapper;

    public final BookingMapper bookingMapper;

    public Optional<Booking> findByEntityIdAndType(
            String entityId,
            EntityType entityType) {

        return bookingRepository.findByEntityIdAndEntityType(
            entityId,
            entityTypeMapper.toPersistence(entityType))
            .map(bookingMapper::toBooking);
    }
}
