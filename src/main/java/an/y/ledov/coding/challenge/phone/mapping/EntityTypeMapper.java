package an.y.ledov.coding.challenge.phone.mapping;

import an.y.ledov.coding.challenge.phone.domain.model.EntityType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EntityTypeMapper {

    an.y.ledov.coding.challenge.phone.adapter.persistence.model.EntityType toPersistence(EntityType type);

}
