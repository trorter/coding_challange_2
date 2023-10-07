package an.y.ledov.coding.challenge.phone.mapping;

import an.y.ledov.coding.challenge.phone.adapter.persistence.model.Phones;
import an.y.ledov.coding.challenge.phone.rest.dto.RestPhoneWithBooking;
import an.y.ledov.coding.challenge.phone.domain.model.Phone;
import an.y.ledov.coding.challenge.phone.rest.dto.RestPhone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PhoneMapper {

    RestPhone toRestPhone(Phone phone);

    RestPhoneWithBooking toPhoneWithBooking(Phone phone);

    Phone toPhone(Phones phone);

}
