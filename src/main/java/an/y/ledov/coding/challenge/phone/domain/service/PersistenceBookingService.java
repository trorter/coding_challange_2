package an.y.ledov.coding.challenge.phone.domain.service;

import an.y.ledov.coding.challenge.phone.domain.model.booking.Booking;
import an.y.ledov.coding.challenge.phone.domain.model.EntityType;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PersistenceBookingService {

    Optional<Booking> findByEntityIdAndType(
        String entityId,
        EntityType entityType);

    Booking bookEntity(
        String entityId,
        EntityType entityType,
        LocalDateTime bookingTime,
        String personName);

}
