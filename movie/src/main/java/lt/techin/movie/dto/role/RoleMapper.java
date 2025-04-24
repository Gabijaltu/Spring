package lt.techin.movie.dto.role;

import lt.techin.movie.model.Role;

import java.util.List;

public class RoleMapper {

  public static Role toRole(RoleRequestDTO roleRequestDTO) {
    return new Role(
            roleRequestDTO.name()
    );
  }

  public static RoleResponseDTO toDTO(Role role) {
    return new RoleResponseDTO(
            role.getId(),
            role.getName()
    );
  }

  public static List<RoleResponseDTO> toListDTO(List<Role> roles) {
    return roles.stream()
            .map(r -> new RoleResponseDTO(
                    r.getId(),
                    r.getName()))
            .toList();
  }
}
