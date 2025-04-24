package lt.techin.movie.model;

import jakarta.persistence.*;

import java.util.List;


@Entity

@Table(name = "movies")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private long id;

  private String title;
  private String director;


  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "movie_id", nullable = false)
  private List<Screening> screenings;

  @ManyToMany
  @JoinTable(
          name = "movie_actors",
          joinColumns = @JoinColumn(name = "movie_id"),
          inverseJoinColumns = @JoinColumn(name = "actor_id")
  )
  private List<Actor> actors;

  public Movie(String title, String director) {
    this.title = title;
    this.director = director;
  }

  public Movie() {
  }

  public Movie(String title, String director, List<Screening> screenings, List<Actor> actors) {
    this.title = title;
    this.director = director;
    this.screenings = screenings;
    this.actors = actors;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public List<Screening> getScreenings() {
    return screenings;
  }

  public List<Actor> getActors() {
    return actors;
  }
}
