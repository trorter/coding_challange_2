package an.y.ledov.coding.challenge.phone.domain.action;

import an.y.ledov.coding.challenge.phone.domain.model.ActionStatus;
import an.y.ledov.coding.challenge.phone.domain.model.booking.Booking;
import an.y.ledov.coding.challenge.phone.domain.model.booking.request.CancelBookingRequest;
import an.y.ledov.coding.challenge.phone.domain.model.booking.request.CreateBookingRequest;
import an.y.ledov.coding.challenge.phone.domain.model.booking.result.BasicBookingResult;
import an.y.ledov.coding.challenge.phone.domain.model.booking.result.CreateBookingResult;
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

    public Optional<Booking> getByEntityIdAndType(
        String id,
        EntityType entityType) {

        return persistenceBookingService.findByEntityIdAndType(id, entityType);

    }

    public Optional<Booking> getById(String id) {
        return persistenceBookingService.findById(id);
    }

    public CreateBookingResult create(CreateBookingRequest createBookingRequest) {

        var validationResult = bookingValidator.validateCreateRequest(createBookingRequest);
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

                var newBooking = bookingService.save(
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

    public BasicBookingResult cancel(CancelBookingRequest cancelBookingRequest) {

        var validationResult = bookingValidator.validateCancelRequest(cancelBookingRequest);
        if (!validationResult.isSuccess()) {
            return BasicBookingResult.builder()
                .status(validationResult.getStatus())
                .message(validationResult.getMessages().stream().reduce((a, b) -> a + "; " + b).orElse(""))
                .build();
        }

        var phone = getById(cancelBookingRequest.getBookingId());
        if (phone.isEmpty()) {
            return BasicBookingResult.builder()
                .status(ActionStatus.FAILURE)
                .message(BookingValidator.BOOKING_NOT_FOUND)
                .build();
        }

        return lockAction.withLock(
            cancelBookingRequest.getEntityId(),
            persistenceBookingService,
            cancelBookingRequest,
            (bookingService, request) -> {

                bookingService.deleteById(request.getBookingId());

                return CreateBookingResult.builder()
                    .status(ActionStatus.SUCCESS)
                    .build();
            });
    }

}
