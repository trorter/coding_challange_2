package an.y.ledov.coding.challenge.phone.domain.service;

import an.y.ledov.coding.challenge.phone.domain.model.phone.Phone;

import java.util.List;
import java.util.Optional;

public interface PersistencePhoneService {

    Optional<Phone> getPhoneById(String id);

    List<Phone> getAll();

}
