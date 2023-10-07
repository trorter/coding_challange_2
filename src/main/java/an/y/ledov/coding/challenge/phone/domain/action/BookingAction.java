package an.y.ledov.coding.challenge.phone.domain.action;

import an.y.ledov.coding.challenge.phone.adapter.persistence.PersistenceBookingInteractor;
import an.y.ledov.coding.challenge.phone.domain.model.Booking;
import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingAction {

    private final PersistenceBookingInteractor persistenceBookingInteractor;

    public Optional<Booking> getBookingByEntityIdAndType(
        String id,
        EntityType entityType) {

        return persistenceBookingInteractor.findByEntityIdAndType(id, entityType);

    }
}
