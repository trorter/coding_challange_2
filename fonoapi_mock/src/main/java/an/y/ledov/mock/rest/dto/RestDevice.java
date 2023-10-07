package an.y.ledov.mock.rest.dto;

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
@Schema(name = "Device")
public class RestDevice {

    @NotNull
    private String device;

    private String brand;

    private String position;

    private String technology;

    private String gprs;

    private String edge;

    private String announced;

    private String status;

    private String dimensions;

    private String weight_g;

    private String weight_oz;

}
