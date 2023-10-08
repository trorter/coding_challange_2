package an.y.ledov.coding.challenge.phone.domain.model.booking.result;

import an.y.ledov.coding.challenge.phone.domain.model.ActionStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
public class BasicBookingResult {

    @NotNull
    protected ActionStatus status;

    protected String message;

    public boolean isSuccess() {
        return ActionStatus.SUCCESS.equals(status);
    }
}
