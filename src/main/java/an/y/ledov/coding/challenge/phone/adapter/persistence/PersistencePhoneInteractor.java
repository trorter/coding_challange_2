package an.y.ledov.coding.challenge.phone.adapter.persistence;

import an.y.ledov.coding.challenge.phone.domain.model.Phone;
import an.y.ledov.coding.challenge.phone.mapping.PhoneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersistencePhoneInteractor {

    private final PhoneRepository phoneRepository;

    private final PhoneMapper phoneMapper;

    public Optional<Phone> getPhoneById(String id) {
        return phoneRepository.findById(id)
            .map(phoneMapper::toPhone);
    }

    public List<Phone> getAll() {
        return phoneRepository.findAll().stream()
            .map(phoneMapper::toPhone)
            .toList();
    }
}
