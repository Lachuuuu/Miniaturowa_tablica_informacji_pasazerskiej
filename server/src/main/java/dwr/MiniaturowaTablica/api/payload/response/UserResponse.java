package dwr.MiniaturowaTablica.api.payload.response;

import dwr.MiniaturowaTablica.api.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
   private String username;
   private String email;
   private Set<Role> roles;
}
