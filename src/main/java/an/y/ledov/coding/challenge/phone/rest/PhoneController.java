package an.y.ledov.coding.challenge.phone.rest;

import an.y.ledov.coding.challenge.phone.rest.dto.RestErrorResponse;
import an.y.ledov.coding.challenge.phone.rest.dto.phone.RestPhoneShort;
import an.y.ledov.coding.challenge.phone.rest.dto.phone.RestPhone;
import an.y.ledov.coding.challenge.phone.rest.interactor.RestPhoneInteractor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phones")
@Tag(name = "Phones", description = "API for phones")
public class PhoneController {

    private final RestPhoneInteractor restPhoneInteractor;

    @Operation(summary = "Get phone",
        description = "Get the phone by ID")
    @GetMapping(value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200",
        description = "Phone was successfully retrieved",
        content = @Content(schema = @Schema(implementation = RestPhone.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404",
        description = "The phone with the given ID was not found",
        content = @Content(schema = @Schema(implementation = RestErrorResponse.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<Object> getPhoneById(
        @PathVariable String id) {

        log.debug("Get phone by ID {}", id);

        var result = restPhoneInteractor.getPhoneById(id);

        log.debug("Get phone by ID {} result is {}", id, result);

        return result;
    }

    @Operation(summary = "Get all phones",
        description = "Get all phones")
    @GetMapping(value = "/",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200",
        description = "Phone was successfully retrieved",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestPhoneShort.class)),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<Object> getAll() {

        log.debug("Get all phones");

        var result = restPhoneInteractor.getAll();

        log.debug("Get all phones result is {}", result);

        return result;
    }
}
