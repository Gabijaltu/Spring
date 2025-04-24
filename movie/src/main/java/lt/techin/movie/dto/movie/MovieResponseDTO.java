package lt.techin.movie.dto.movie;

import lt.techin.movie.model.Actor;
import lt.techin.movie.model.Screening;

import java.util.List;

public record MovieResponseDTO(
        long id,
        String title,
        String director,
        List<Screening> screenings,
        List<Actor> actors
) {
}
