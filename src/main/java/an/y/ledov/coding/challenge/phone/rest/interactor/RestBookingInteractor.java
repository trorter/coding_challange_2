package an.y.ledov.coding.challenge.phone.rest.interactor;

import an.y.ledov.coding.challenge.phone.domain.action.BookingAction;
import an.y.ledov.coding.challenge.phone.domain.model.booking.request.CancelBookingRequest;
import an.y.ledov.coding.challenge.phone.domain.model.booking.request.CreateBookingRequest;
import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import an.y.ledov.coding.challenge.phone.mapping.BookingMapper;
import an.y.ledov.coding.challenge.phone.rest.dto.RestErrorResponse;
import an.y.ledov.coding.challenge.phone.rest.dto.booking.RestCreateBooking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestBookingInteractor {

    private final BookingAction bookingAction;

    private final BookingMapper bookingMapper;

    public ResponseEntity<Object> bookPhone(
        String phoneId,
        RestCreateBooking bookingInfo) {

        var result = bookingAction.create(
            CreateBookingRequest.builder()
                .entityId(phoneId)
                .entityType(EntityType.PHONE)
                .personName(bookingInfo.getPersonName())
                .build());

        return result.isSuccess()
            ? ResponseEntity.ok().body(bookingMapper.toRestBooking(result.getBooking()))
            : ResponseEntity.badRequest().body(RestErrorResponse.builder()
            .errorMessage(result.getMessage())
            .build());
    }

    public ResponseEntity<Object> cancel(
        String phoneId,
        String bookingId) {

        var result = bookingAction.cancel(CancelBookingRequest.builder()
            .bookingId(bookingId)
            .entityId(phoneId)
            .entityType(EntityType.PHONE)
            .build());

        return result.isSuccess()
            ? ResponseEntity.ok().build()
            : ResponseEntity.badRequest()
                .body(RestErrorResponse.builder()
                .errorMessage(result.getMessage())
                .build());
    }
}
