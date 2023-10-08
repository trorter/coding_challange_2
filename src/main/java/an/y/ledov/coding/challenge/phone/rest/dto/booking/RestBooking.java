package an.y.ledov.coding.challenge.phone.rest.dto.booking;

import an.y.ledov.coding.challenge.phone.rest.dto.RestEntityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Booking")
public class RestBooking {

    @NotNull
    private String id;

    @NotNull
    private String entityId;

    @NotNull
    private RestEntityType entityType;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private String personName;

}
