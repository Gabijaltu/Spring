package lt.techin.movie.dto.actor;

import jakarta.validation.constraints.Pattern;
import org.antlr.v4.runtime.misc.NotNull;

public record ActorRequestDTO(
        @NotNull
        @Pattern(regexp = "^[A-Za-z ]+$", message = "Only letters")
        String name,

        @NotNull
        @Pattern(regexp = "^[A-Za-z ]+$", message = "Only letters")
        String surname
) {
}
