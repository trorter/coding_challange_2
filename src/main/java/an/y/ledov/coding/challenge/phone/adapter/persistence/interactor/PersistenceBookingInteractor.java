package an.y.ledov.coding.challenge.phone.adapter.persistence.interactor;

import an.y.ledov.coding.challenge.phone.adapter.persistence.BookingRepository;
import an.y.ledov.coding.challenge.phone.adapter.persistence.model.Bookings;
import an.y.ledov.coding.challenge.phone.domain.model.booking.Booking;
import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import an.y.ledov.coding.challenge.phone.domain.service.PersistenceBookingService;
import an.y.ledov.coding.challenge.phone.mapping.BookingMapper;
import an.y.ledov.coding.challenge.phone.mapping.EntityTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersistenceBookingInteractor implements PersistenceBookingService {

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

    public Booking save(
            String entityId,
            EntityType entityType,
            LocalDateTime bookingTime,
            String personName) {

        var booking = Bookings.builder()
            .entityId(entityId)
            .entityType(entityTypeMapper.toPersistence(entityType))
            .startDate(bookingTime)
            .personName(personName)
            .build();

        return bookingMapper.toBooking(bookingRepository.save(booking));
    }

    public Optional<Booking> findById(String bookingId) {
        return bookingRepository.findById(bookingId)
            .map(bookingMapper::toBooking);
    }

    public void deleteById(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
