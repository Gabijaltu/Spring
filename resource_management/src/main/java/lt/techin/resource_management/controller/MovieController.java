package lt.techin.resource_management.controller;

import lt.techin.resource_management.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

  private List<Movie> movies = new ArrayList<>(List.of(new Movie(1, "The Shawshank Redemption", "Frank Darabont"),
          new Movie(2, "Schindler's List", "Steven Spielberg")));

  @GetMapping("/movies")
  public ResponseEntity<List<Movie>> getMovies() {
    return ResponseEntity.ok(movies);
  }

  @GetMapping("/movies/{index}")
  public ResponseEntity<Movie> getMovie(@PathVariable int index) {
    if (index > movies.size() - 1) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(movies.get(index));
  }

  @PostMapping("/movies")
  public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
    if (movie.getTitle().isEmpty() || movie.getDirector().isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    movies.add(movie);

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{index}")
                            .buildAndExpand(movies.size() - 1)
                            .toUri())
            .body(movie);
  }

  @GetMapping("/movies/search")
  public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title) {
    Optional<Movie> foundMovie = movies.stream()
            .filter(b -> b.getTitle().contains(title))
            .findFirst();

    if (foundMovie.isEmpty()) {
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.ok(foundMovie.get());
  }
}
