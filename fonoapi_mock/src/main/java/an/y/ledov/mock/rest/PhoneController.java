package an.y.ledov.mock.rest;


import an.y.ledov.mock.rest.dto.RestDevice;
import an.y.ledov.mock.service.PhoneService;
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
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Phones", description = "API for phones")
public class PhoneController {

    private final PhoneService phoneService;

    @Operation(summary = "Search for devices",
        description = "Get the phone by ID")
    @GetMapping(value = "/getdevice",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200",
        description = "Devices were successfully retrieved",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestDevice.class)),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<List<RestDevice>> getDevices(
        @RequestParam String token,
        @Nullable
        @RequestParam String brand,
        @RequestParam String device,
        @Nullable
        @RequestParam String position) {

        log.debug("Get devices by token {}, brand {}, device {}, position {}",
            token,
            brand,
            device,
            position);

        var result = phoneService.findDevices(brand, device, position);

        return ResponseEntity.ok()
            .body(result);
    }

}
