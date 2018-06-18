package uk.co.datadisk.ddflix.dto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.datadisk.ddflix.entities.user.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEditFormDTO {

    private Long id;
    private String dateCreated;

    @Email
    @Size(min = 10, max = 50)
    private String email;

    @Size(min = 5, max = 15)
    private String username;

    private boolean active;
    private boolean enabled;
    private boolean expired;
    private boolean locked;
    private Set<Role> roles = new HashSet<>();
}
