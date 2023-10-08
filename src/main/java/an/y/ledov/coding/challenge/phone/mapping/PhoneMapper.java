package an.y.ledov.coding.challenge.phone.mapping;

import an.y.ledov.coding.challenge.phone.adapter.extended.phone.dto.RestDevice;
import an.y.ledov.coding.challenge.phone.adapter.persistence.model.Phones;
import an.y.ledov.coding.challenge.phone.rest.dto.phone.RestPhone;
import an.y.ledov.coding.challenge.phone.domain.model.phone.Phone;
import an.y.ledov.coding.challenge.phone.rest.dto.phone.RestPhoneShort;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PhoneMapper {

    RestPhoneShort toRestPhone(Phone phone);

    RestPhone toPhoneWithBooking(Phone phone);

    Phone toPhone(Phones phone);

    Phone.ExtendedParams toExtendedParams(RestDevice phone);

    RestPhone.RestExtendedParams toRestExtendedParams(Phone.ExtendedParams phone);
}
