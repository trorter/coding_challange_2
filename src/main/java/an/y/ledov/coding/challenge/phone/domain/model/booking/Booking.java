package an.y.ledov.coding.challenge.phone.domain.model.booking;

import an.y.ledov.coding.challenge.phone.rest.dto.RestEntityType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@Builder
public class Booking {

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
