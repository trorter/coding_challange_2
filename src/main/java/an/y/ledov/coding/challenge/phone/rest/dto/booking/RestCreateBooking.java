package an.y.ledov.coding.challenge.phone.rest.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateBooking")
public class RestCreateBooking {

    @NotNull
    private String personName;
}
