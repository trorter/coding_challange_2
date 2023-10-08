package an.y.ledov.coding.challenge.phone.domain.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

import java.util.List;

@Data
@Builder
public class ValidationResult {

    @NotNull
    private ActionStatus status;

    private List<String> messages;

    public boolean isSuccess() {
        return ActionStatus.SUCCESS.equals(status);
    }
}
