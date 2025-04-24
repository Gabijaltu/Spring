package lt.techin.movie.dto.role;

import jakarta.validation.constraints.NotNull;

public record RoleRequestDTO(
        @NotNull
        String name
) {
}
