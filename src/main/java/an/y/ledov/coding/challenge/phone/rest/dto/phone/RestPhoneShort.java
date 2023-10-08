package an.y.ledov.coding.challenge.phone.rest.dto.phone;

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
@Schema(name = "PhoneShort")
public class RestPhoneShort {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private Boolean availableForBooking;

}
