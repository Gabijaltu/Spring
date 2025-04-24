package lt.techin.movie.repository;

import lt.techin.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  Optional<Movie> findByTitle(String title);

  boolean existsByTitle(String title);

  boolean existsByDirector(String director);

  List<Movie> findAllByTitle(String title);
}
