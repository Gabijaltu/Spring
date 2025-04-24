package lt.techin.movie.dto.user;

import lt.techin.movie.model.Role;

import java.util.List;

public record UserResponseDTO(
        long id,
        String username,
        List<Role> roles
) {
}
