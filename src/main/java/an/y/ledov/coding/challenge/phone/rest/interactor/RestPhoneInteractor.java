package an.y.ledov.coding.challenge.phone.rest.interactor;

import an.y.ledov.coding.challenge.phone.domain.action.BookingAction;
import an.y.ledov.coding.challenge.phone.domain.action.PhoneAction;
import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import an.y.ledov.coding.challenge.phone.mapping.BookingMapper;
import an.y.ledov.coding.challenge.phone.mapping.PhoneMapper;
import an.y.ledov.coding.challenge.phone.rest.dto.RestErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestPhoneInteractor {

    public static final String PHONE_NOT_FOUND = "Phone not found";

    private final PhoneAction phoneAction;

    private final BookingAction bookingAction;

    private final PhoneMapper phoneMapper;

    private final BookingMapper bookingMapper;

    public ResponseEntity<Object> getById(String id) {
        var getPhone = phoneAction.getById(id);

        if (getPhone.isEmpty()) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RestErrorResponse.builder()
                    .errorMessage(PHONE_NOT_FOUND)
                    .build());
        }

        var getBooking = bookingAction.getByEntityIdAndType(id, EntityType.PHONE);

        var result = phoneMapper.toPhoneWithBooking(getPhone.get());
        result.setAvailableForBooking(getBooking.isEmpty());
        getBooking.ifPresent(value -> result.setBooking(bookingMapper.toRestBooking(value)));

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Object> getAll() {
        var phones = phoneAction.getAll().stream()
            .map(phoneMapper::toRestPhone)
            .toList();

        phones.forEach(phone -> phone.setAvailableForBooking(
            bookingAction.getByEntityIdAndType(phone.getId(), EntityType.PHONE).isEmpty()));

        return ResponseEntity.ok(phones);
    }

}
