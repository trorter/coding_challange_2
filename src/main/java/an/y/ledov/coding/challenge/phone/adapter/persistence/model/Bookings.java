package an.y.ledov.coding.challenge.phone.adapter.persistence.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookings")
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompoundIndex(
    name = "lock_entity_search",
    def = "{'e_id': 1, 'e_t': 1}")
public class Bookings {

    @Id
    private String id;

    @Version
    private Long version;

    @Field("e_id")
    private String entityId;

    @Field("e_t")
    private EntityType entityType;

    @Field("s_d")
    private LocalDateTime startDate;

    @Field("p_n")
    private String personName;

}
