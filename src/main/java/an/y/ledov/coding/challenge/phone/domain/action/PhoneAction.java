package an.y.ledov.coding.challenge.phone.domain.action;

import an.y.ledov.coding.challenge.phone.adapter.persistence.PersistencePhoneInteractor;
import an.y.ledov.coding.challenge.phone.domain.model.Phone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PhoneAction {

    private final PersistencePhoneInteractor persistencePhoneInteractor;

    public Optional<Phone> getPhoneById(String id) {

        var getPhone = persistencePhoneInteractor.getPhoneById(id);

        if (getPhone.isEmpty()) {
            log.info("Phone with id {} not found", id);

            return Optional.empty();
        }


        return Optional.of(Phone.builder()
            .id(id)
            .name("Phone " + id)
            .build());
    }

    public List<Phone> getAll() {
        return persistencePhoneInteractor.getAll();
    }

}
