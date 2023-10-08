package an.y.ledov.coding.challenge.phone.domain.action;

import an.y.ledov.coding.challenge.phone.domain.model.phone.Phone;
import an.y.ledov.coding.challenge.phone.domain.service.ExtendedPhoneService;
import an.y.ledov.coding.challenge.phone.domain.service.PersistencePhoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PhoneAction {

    private final PersistencePhoneService persistencePhoneService;

    private final ExtendedPhoneService extendedPhoneService;

    public Optional<Phone> getPhoneById(String id) {

        var getPhone = persistencePhoneService.getPhoneById(id);

        if (getPhone.isEmpty()) {
            log.info("Phone with id {} not found", id);

            return Optional.empty();
        }

        return getPhone.map(phone -> {
            extendedPhoneService.getExtendedInformation(phone.getName())
                .ifPresent(phone::setExtendedParams);
            return phone;
        });
    }

    public List<Phone> getAll() {
        return persistencePhoneService.getAll();
    }

}
