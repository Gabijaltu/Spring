package lt.techin.movie.controller;

import jakarta.validation.Valid;
import lt.techin.movie.dto.movie.MovieMapper;
import lt.techin.movie.dto.movie.MovieRequestDTO;
import lt.techin.movie.dto.movie.MovieResponseDTO;
import lt.techin.movie.model.Movie;
import lt.techin.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("/movies")
  public ResponseEntity<List<MovieResponseDTO>> getMovies() {
    List<Movie> movies = this.movieService.findAllMovies();

    return ResponseEntity.ok(MovieMapper.toListDTO(movies));
  }

  @GetMapping("/movies/{id}")
  public ResponseEntity<MovieResponseDTO> getMovie(@Valid @PathVariable long id) {

    Optional<Movie> movie = this.movieService.findMovieById(id);

    if (movie.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(MovieMapper.toDTO(movie.get()));
  }

  @PostMapping("/movies")
  public ResponseEntity<Object> saveMovie(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {

    if (this.movieService.existsByTitle(movieRequestDTO.title()) && this.movieService.existsByDirector(movieRequestDTO.director())) {
      Map<String, String> response = new HashMap<>();
      response.put("message", "Movie with such a title and director already exists");

      return ResponseEntity.badRequest().body(response);
    }
    Movie savedMovie = this.movieService.saveMovie(MovieMapper.toMovie(movieRequestDTO));

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedMovie.getId())
                            .toUri())
            .body(MovieMapper.toDTO(savedMovie));

  }

  @PutMapping("/movies/{id}")
  public ResponseEntity<Object> updateMovie(@Valid @PathVariable int id, @RequestBody MovieRequestDTO movieRequestDTO) {

    if (this.movieService.existsByTitle(movieRequestDTO.title()) && this.movieService.existsByDirector(movieRequestDTO.director())) {
      Map<String, String> response = new HashMap<>();
      response.put("message", "Movie with such a title and director already exists");

      return ResponseEntity.badRequest().body(response);
    }

    Optional<Movie> movieFromDb = this.movieService.findMovieById(id);

    if (movieFromDb.isPresent()) {

      Movie updatedMovie = movieFromDb.get();

      updatedMovie.setTitle(movieRequestDTO.title());
      updatedMovie.setDirector(movieRequestDTO.director());

      Movie m = this.movieService.saveMovie(updatedMovie);

      return ResponseEntity.ok(this.movieService.saveMovie(updatedMovie));
    }

    Movie savedMovie = this.movieService.saveMovie(MovieMapper.toMovie(movieRequestDTO));

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .replacePath("/api/movies/{index}")
                            .buildAndExpand(savedMovie.getId())
                            .toUri())
            .body(MovieMapper.toDTO(savedMovie));
  }

  @GetMapping("/movies/search")
  public ResponseEntity<List<MovieResponseDTO>> getMoviesByTitle(@RequestParam String title) {
    List<Movie> movies = this.movieService.findMoviesByTitle(title);

    if (movies.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(MovieMapper.toListDTO(movies));
  }

  @DeleteMapping("/movies/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable long id) {

    Optional<Movie> movie = this.movieService.findMovieById(id);

    if (movie.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    this.movieService.deleteMovieById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/movies/pagination")
  public ResponseEntity<Page<Movie>> getMoviesPage(@RequestParam int page, @RequestParam int size) {
    return ResponseEntity.ok(movieService.findAllMoviesPage(page, size));
  }
}
