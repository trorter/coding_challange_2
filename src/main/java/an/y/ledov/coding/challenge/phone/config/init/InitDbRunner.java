package an.y.ledov.coding.challenge.phone.config.init;

import an.y.ledov.coding.challenge.phone.adapter.persistence.PhoneRepository;
import an.y.ledov.coding.challenge.phone.adapter.persistence.model.Phones;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDbRunner implements CommandLineRunner {


    private final PhoneRepository phoneRepository;

    static final List<Phones> listOfRecords = List.of(
        Phones.builder()
            .name("Samsung Galaxy S9")
            .build(),
        Phones.builder()
            .name("Samsung Galaxy S8")
            .build(),
        Phones.builder()
            .name("Samsung Galaxy S8")
            .build(),
        Phones.builder()
            .name("Motorola Nexus 6")
            .build(),
        Phones.builder()
            .name("Oneplus 9")
            .build(),
        Phones.builder()
            .name("Apple iPhone 13")
            .build(),
        Phones.builder()
            .name("Apple iPhone 12")
            .build(),
        Phones.builder()
            .name("Apple iPhone 11")
            .build(),
        Phones.builder()
            .name("iPhone X")
            .build(),
        Phones.builder()
            .name("Nokia 3310")
            .build()
    );

    @Override
    public void run(String... args) {
        if (phoneRepository.count() > 0) {
            return;
        }

        phoneRepository.saveAll(listOfRecords);
    }

}
