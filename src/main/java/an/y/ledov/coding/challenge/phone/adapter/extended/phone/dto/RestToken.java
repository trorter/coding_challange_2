package an.y.ledov.coding.challenge.phone.adapter.extended.phone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestToken {

    @NotNull
    private String token;
}
