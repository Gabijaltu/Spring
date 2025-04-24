package lt.techin.movie.dto.screening;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScreeningResponseDTO(
        long id,
        String theater,
        LocalDate date,
        LocalTime time
) {
}
