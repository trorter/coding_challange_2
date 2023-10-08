package an.y.ledov.coding.challenge.phone.domain.model.phone;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class Phone {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @Nullable
    private ExtendedParams extendedParams;

    @Data
    @Builder
    public static class ExtendedParams {

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
