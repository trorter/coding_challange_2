package an.y.ledov.coding.challenge.phone.adapter.persistence;

import an.y.ledov.coding.challenge.phone.adapter.persistence.model.Bookings;
import an.y.ledov.coding.challenge.phone.adapter.persistence.model.EntityType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookingRepository extends MongoRepository<Bookings, String> {

    Optional<Bookings> findByEntityIdAndEntityType(String entityId, EntityType entityType);

}
