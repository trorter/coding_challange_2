package an.y.ledov.coding.challenge.phone.adapter.persistence.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookings")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bookings {

    @Id
    private String id;

    @JsonProperty("e_id")
    private String entityId;

    @JsonProperty("e_t")
    private EntityType entityType;

    @JsonProperty("s_d")
    private LocalDateTime startDate;

    @JsonProperty("p_n")
    private String personName;

}
