package lt.techin.movie.dto.screening;

import jakarta.validation.constraints.Pattern;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScreeningRequestDTO(
        @NotNull
        @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Only letters and numbers allowed")
        String theater,

        @NotNull
        @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message = "Date format must be YYYY-MM-DD")
        LocalDate date,

        @NotNull
        @Pattern(regexp = "^[0-2][0-3]:[0-5][0-9]$", message = "Time format must be HH:MM")
        LocalTime time
) {
}
