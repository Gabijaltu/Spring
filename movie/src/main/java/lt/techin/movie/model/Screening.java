package lt.techin.movie.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity

@Table(name = "screenings")
public class Screening {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String theater;
  private LocalDate date;
  private LocalTime time;


  public Screening(String theater, LocalDate date, LocalTime time) {
    this.theater = theater;
    this.date = date;
    this.time = time;
  }

  public Screening() {
  }

  public long getId() {
    return id;
  }

  public String getTheater() {
    return theater;
  }

  public LocalDate getDate() {
    return date;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTheater(String theater) {
    this.theater = theater;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }
}
