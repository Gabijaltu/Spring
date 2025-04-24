package lt.techin.movie.dto.actor;

import lt.techin.movie.model.Actor;

import java.util.List;

public class ActorMapper {

  public static Actor toActor(ActorRequestDTO actorRequestDTO) {
    return new Actor(
            actorRequestDTO.name(),
            actorRequestDTO.surname());
  }

  public static ActorResponseDTO toDTO(Actor actor) {
    return new ActorResponseDTO(
            actor.getId(),
            actor.getName(),
            actor.getSurname()
    );
  }

  public static List<ActorResponseDTO> toListDTO(List<Actor> actors) {
    return actors.stream()
            .map(a -> new ActorResponseDTO(
                    a.getId(),
                    a.getName(),
                    a.getSurname()))
            .toList();
  }

}
