package an.y.ledov.coding.challenge.phone.domain.validation;

import an.y.ledov.coding.challenge.phone.domain.action.PhoneAction;
import an.y.ledov.coding.challenge.phone.domain.model.ActionStatus;
import an.y.ledov.coding.challenge.phone.domain.model.booking.CreateBookingRequest;
import an.y.ledov.coding.challenge.phone.domain.model.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class BookingValidator {

    public static final String PERSON_NAME_IS_BLANK = "Person name is blank";

    public static final String PHONE_NOT_FOUND = "Phone not found";

    public static final String ENTITY_TYPE_NOT_SUPPORTED = "Entity type not supported";

    private final PhoneAction phoneAction;

    public ValidationResult validateBookingRequest(
        CreateBookingRequest createBookingRequest) {

        var errorList = new ArrayList<String>();
        var builder = ValidationResult.builder()
            .messages(errorList)
            .status(ActionStatus.SUCCESS);

        if (Strings.isBlank(createBookingRequest.getPersonName())) {
            builder.status(ActionStatus.FAILURE);
            errorList.add(PERSON_NAME_IS_BLANK);
        }

        switch (createBookingRequest.getEntityType()) {
            case PHONE:
                var phone = phoneAction.getPhoneById(createBookingRequest.getEntityId());
                if (phone.isEmpty()) {
                    builder.status(ActionStatus.FAILURE);
                    errorList.add(PHONE_NOT_FOUND);
                }
                break;
            default:
                builder.status(ActionStatus.FAILURE);
                errorList.add(ENTITY_TYPE_NOT_SUPPORTED);
        }

        return builder.build();
    }
}
