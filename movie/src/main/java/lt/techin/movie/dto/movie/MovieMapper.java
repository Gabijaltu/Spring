package lt.techin.movie.dto.movie;

import lt.techin.movie.model.Movie;

import java.util.List;

public class MovieMapper {

  public static Movie toMovie(MovieRequestDTO movieRequestDTO) {
    return new Movie(
            movieRequestDTO.title(),
            movieRequestDTO.director(),
            movieRequestDTO.screenings(),
            movieRequestDTO.actors());
  }

  public static MovieResponseDTO toDTO(Movie movie) {
    return new MovieResponseDTO(
            movie.getId(),
            movie.getTitle(),
            movie.getDirector(),
            movie.getScreenings(),
            movie.getActors()
    );
  }

  public static List<MovieResponseDTO> toListDTO(List<Movie> movies) {
    return movies.stream()
            .map(m -> new MovieResponseDTO(
                    m.getId(),
                    m.getTitle(),
                    m.getDirector(),
                    m.getScreenings(),
                    m.getActors()))
            .toList();
  }

}
