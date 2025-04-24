package lt.techin.movie.dto.movie;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.movie.model.Actor;
import lt.techin.movie.model.Screening;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public record MovieRequestDTO(
        @NotNull
        @Size(min = 2, max = 120)
        String title,

        @NotNull
        @Pattern(regexp = "^[A-Z][A-Za-z ]+$", message = "Must start with uppercase and only letters")
        String director,

        @NotNull
        List<Screening> screenings,

        @NotNull
        List<Actor> actors
) {
}
