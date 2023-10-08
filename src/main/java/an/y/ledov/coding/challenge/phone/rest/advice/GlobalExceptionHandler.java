package an.y.ledov.coding.challenge.phone.rest.advice;

import an.y.ledov.coding.challenge.phone.rest.dto.RestErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestErrorResponse> handleValidationExceptions(
        MissingServletRequestParameterException exception) {

        log.debug("MissingServletRequestParameterException: {}", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RestErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .build());

    }
}
