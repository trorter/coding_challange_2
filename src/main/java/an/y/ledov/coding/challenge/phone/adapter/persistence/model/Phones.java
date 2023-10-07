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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "phones")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phones {

    @Id
    private String id;

    @JsonProperty("n")
    private String name;

}
