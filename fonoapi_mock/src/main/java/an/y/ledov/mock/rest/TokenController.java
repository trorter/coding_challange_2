package an.y.ledov.mock.rest;


import an.y.ledov.mock.rest.dto.RestErrorResponse;
import an.y.ledov.mock.rest.dto.RestToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@Tag(name = "Tokens", description = "API for tokens")
public class TokenController {

    @Operation(summary = "Generate token",
        description = "Get the phone by ID")
    @GetMapping(value = "/generate",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200",
        description = "Phone was successfully retrieved",
        content = @Content(schema = @Schema(implementation = RestToken.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "400",
        description = "Bad request",
        content = @Content(schema = @Schema(implementation = RestErrorResponse.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<RestToken> generate() {
        return ResponseEntity.ok()
            .body(RestToken.builder()
                .token(UUID.randomUUID().toString())
                .build());
    }

}
