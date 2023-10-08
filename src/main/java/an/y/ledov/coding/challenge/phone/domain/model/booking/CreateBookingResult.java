package an.y.ledov.coding.challenge.phone.domain.model.booking;

import an.y.ledov.coding.challenge.phone.domain.model.ActionStatus;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateBookingResult {

    @NotNull
    private ActionStatus status;

    private String message;

    private Booking booking;

    public boolean isSuccess() {
        return ActionStatus.SUCCESS.equals(status);
    }
}
