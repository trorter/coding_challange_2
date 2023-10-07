package an.y.ledov.coding.challenge.phone.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PhoneWithBooking")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestPhoneWithBooking {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private Boolean availableForBooking;

    @Nullable
    private RestBooking booking;

}
