package an.y.ledov.coding.challenge.phone.rest;

import an.y.ledov.coding.challenge.phone.rest.dto.RestErrorResponse;
import an.y.ledov.coding.challenge.phone.rest.dto.booking.RestCreateBooking;
import an.y.ledov.coding.challenge.phone.rest.interactor.RestBookingInteractor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phones/{phone_id}/bookings")
@Tag(name = "Booking", description = "API for bookings")
public class BookingController {

    private final RestBookingInteractor restBookingInteractor;

    @Operation(summary = "Create a booking",
        description = "Create a booking for a phone")
    @PostMapping(value = "/",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200",
        description = "Booking was made",
        content = @Content(schema = @Schema(implementation = RestCreateBooking.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404",
        description = "The phone with the given ID was not found",
        content = @Content(schema = @Schema(implementation = RestErrorResponse.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "400",
        description = "The phone is already booked",
        content = @Content(schema = @Schema(implementation = RestErrorResponse.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "400",
        description = "Bad request",
        content = @Content(schema = @Schema(implementation = RestErrorResponse.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<Object> create(
        @PathVariable("phone_id") String phoneId,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                examples = {
                    @ExampleObject(
                        name = "Basic",
                        description = "Request body",
                        extensions = @Extension(properties = @ExtensionProperty(name = "", value = "", parseValue = true)),
                        value = """
                                                    {
                                                      "personName": "John Doe"
                                                    }
                                                    """),
                }))
        @RequestBody @Valid RestCreateBooking restBooking) {

        log.debug("Create booking for phone {} with the following data {}", phoneId, restBooking);

        var result = restBookingInteractor.bookPhone(phoneId, restBooking);

        log.debug("Booking was created this the result {}", result);

        return result;
    }

    @Operation(summary = "Cancel a booking",
        description = "Cancel a booking for a phone")
    @DeleteMapping(value = "/{booking_id}")
    @ApiResponse(responseCode = "200",
        description = "Booking was canceled",
        content = @Content(schema = @Schema(implementation = RestCreateBooking.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404",
        description = "The phone with the given ID was not found",
        content = @Content(schema = @Schema(implementation = RestErrorResponse.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404",
        description = "The booking with the given ID was not found",
        content = @Content(schema = @Schema(implementation = RestErrorResponse.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<Object> cancel(
        @PathVariable("phone_id") String phoneId,
        @PathVariable("booking_id") String bookingId) {

        log.debug("Cancel booking for phone {} with the following data {}", phoneId, bookingId);

        var result = restBookingInteractor.cancel(phoneId, bookingId);

        log.debug("Booking was canceled this the result {}", result);

        return result;
    }

}
