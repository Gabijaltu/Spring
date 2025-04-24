package lt.techin.movie.model;


import jakarta.persistence.*;

@Entity
@Table(name = "actors")

public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;


  private String surname;

  public Actor(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  public Actor() {
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }
}

