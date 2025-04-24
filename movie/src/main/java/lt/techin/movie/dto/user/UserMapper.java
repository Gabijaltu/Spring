package lt.techin.movie.dto.user;

import lt.techin.movie.model.User;

import java.util.List;

public class UserMapper {

  public static User toUser(UserRequestDTO userRequestDTO) {
    return new User(
            userRequestDTO.username(),
            userRequestDTO.password(),
            userRequestDTO.roles()
    );
  }

  public void setPassword(String encode) {
  }

  public static UserResponseDTO toDTO(User user) {
    return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getRoles()
    );
  }

  public static List<UserResponseDTO> toListDTO(List<User> users) {
    return users.stream()
            .map(u -> new UserResponseDTO(
                    u.getId(),
                    u.getUsername(),
                    u.getRoles()))
            .toList();
  }
}
