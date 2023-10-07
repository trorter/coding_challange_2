package an.y.ledov.coding.challenge.phone.adapter.persistence;

import an.y.ledov.coding.challenge.phone.adapter.persistence.model.Phones;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhoneRepository extends MongoRepository<Phones, String> {

}
