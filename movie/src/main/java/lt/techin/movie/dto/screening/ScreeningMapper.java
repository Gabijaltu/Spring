package lt.techin.movie.dto.screening;

import lt.techin.movie.model.Screening;

import java.util.List;

public class ScreeningMapper {

  public static Screening toScreening(ScreeningRequestDTO screeningRequestDTO) {
    return new Screening(
            screeningRequestDTO.theater(),
            screeningRequestDTO.date(),
            screeningRequestDTO.time()
    );
  }

  public static ScreeningResponseDTO toDTO(Screening screening) {
    return new ScreeningResponseDTO(
            screening.getId(),
            screening.getTheater(),
            screening.getDate(),
            screening.getTime()
    );
  }

  public static List<ScreeningResponseDTO> toListDTO(List<Screening> screenings) {
    return screenings.stream()
            .map(s -> new ScreeningResponseDTO(
                    s.getId(),
                    s.getTheater(),
                    s.getDate(),
                    s.getTime()))
            .toList();
  }

}
