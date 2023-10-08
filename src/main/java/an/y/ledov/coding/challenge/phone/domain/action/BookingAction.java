package an.y.ledov.coding.challenge.phone.domain.action;

import an.y.ledov.coding.challenge.phone.domain.model.ActionStatus;
import an.y.ledov.coding.challenge.phone.domain.model.booking.Booking;
import an.y.ledov.coding.challenge.phone.domain.model.booking.CreateBookingRequest;
import an.y.ledov.coding.challenge.phone.domain.model.booking.CreateBookingResult;
import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import an.y.ledov.coding.challenge.phone.domain.service.PersistenceBookingService;
import an.y.ledov.coding.challenge.phone.domain.validation.BookingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingAction {

    private final BookingValidator bookingValidator;

    private final LockAction lockAction;

    private final PersistenceBookingService persistenceBookingService;

    public Optional<Booking> getBookingByEntityIdAndType(
        String id,
        EntityType entityType) {

        return persistenceBookingService.findByEntityIdAndType(id, entityType);

    }

    public CreateBookingResult createBooking(CreateBookingRequest createBookingRequest) {

        var validationResult = bookingValidator.validateBookingRequest(createBookingRequest);
        if (!validationResult.isSuccess()) {
            return CreateBookingResult.builder()
                .status(validationResult.getStatus())
                .message(validationResult.getMessages().stream().reduce((a, b) -> a + "; " + b).orElse(""))
                .build();
        }

        return lockAction.withLock(
            createBookingRequest.getEntityId(),
            persistenceBookingService,
            createBookingRequest,
            (bookingService, request) -> {

                var findBooking = persistenceBookingService.findByEntityIdAndType(
                    createBookingRequest.getEntityId(),
                    createBookingRequest.getEntityType());

                if (findBooking.isPresent()) {
                    return CreateBookingResult.builder()
                        .status(ActionStatus.FAILURE)
                        .message("Booking already exists")
                        .build();
                }

                var newBooking = bookingService.bookEntity(
                    request.getEntityId(),
                    request.getEntityType(),
                    LocalDateTime.now(),
                    request.getPersonName());

                return CreateBookingResult.builder()
                    .status(ActionStatus.SUCCESS)
                    .booking(newBooking)
                    .build();
            });
    }

}
