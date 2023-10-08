package an.y.ledov.coding.challenge.phone.domain.service;

import an.y.ledov.coding.challenge.phone.domain.model.phone.Phone;

import java.util.Optional;

public interface ExtendedPhoneService {

    Optional<Phone.ExtendedParams> getInformation(String name);

}
