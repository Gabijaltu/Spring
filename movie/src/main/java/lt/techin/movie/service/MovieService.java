package lt.techin.movie.service;

import lt.techin.movie.model.Movie;
import lt.techin.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

  private final MovieRepository movieRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> findAllMovies() {
    return this.movieRepository.findAll();
  }

  public Optional<Movie> findMovieById(long id) {
    return this.movieRepository.findById(id);
  }

  public Movie saveMovie(Movie movie) {
    return this.movieRepository.save(movie);
  }

  public List<Movie> findMoviesByTitle(String title) {
    return movieRepository.findAllByTitle(title);
  }

  public void deleteMovieById(long id) {
    this.movieRepository.deleteById(id);
  }

  public boolean existsByTitle(String title) {
    return this.movieRepository.existsByTitle(title);
  }

  public boolean existsByDirector(String director) {
    return this.movieRepository.existsByDirector(director);
  }

  public Page<Movie> findAllMoviesPage(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return movieRepository.findAll(pageable);
  }
}
