package dwr.MiniaturowaTablica.api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    public ERole getName() {
        return name;
    }

    private ERole name;

}
