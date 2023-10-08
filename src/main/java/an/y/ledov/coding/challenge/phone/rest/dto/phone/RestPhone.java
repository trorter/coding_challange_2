package an.y.ledov.coding.challenge.phone.rest.dto.phone;

import an.y.ledov.coding.challenge.phone.rest.dto.booking.RestBooking;
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
@Schema(name = "Phone")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestPhone {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private Boolean availableForBooking;

    @Nullable
    private RestBooking booking;

    @Nullable
    private RestExtendedParams extendedParams;

    @Data
    @Builder
    @Schema(name = "ExtendedParams")
    public static class RestExtendedParams {

        @NotNull
        private String device;

        @NotNull
        private String brand;

        @Nullable
        private String position;

        @Nullable
        private String technology;

        @Nullable
        private String gprs;

        @Nullable
        private String edge;

        @Nullable
        private String announced;

        @Nullable
        private String status;

        @Nullable
        private String dimensions;

        @Nullable
        private String weight_g;

        @Nullable
        private String weight_oz;
    }

}
