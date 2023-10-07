package an.y.ledov.coding.challenge.phone.rest.dto;

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
@Schema(name = "Phone")
public class RestPhone {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private Boolean availableForBooking;

}
