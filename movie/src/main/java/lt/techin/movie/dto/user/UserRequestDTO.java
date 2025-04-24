package lt.techin.movie.dto.user;

import jakarta.validation.constraints.NotNull;
import lt.techin.movie.model.Role;

import java.util.List;

public record UserRequestDTO(
        @NotNull
        String username,

        @NotNull
        String password,
        List<Role> roles
) {

}
