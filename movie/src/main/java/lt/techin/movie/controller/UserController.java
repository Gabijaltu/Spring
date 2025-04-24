package lt.techin.movie.controller;

import jakarta.validation.Valid;
import lt.techin.movie.dto.user.UserMapper;
import lt.techin.movie.dto.user.UserRequestDTO;
import lt.techin.movie.dto.user.UserResponseDTO;
import lt.techin.movie.model.User;
import lt.techin.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    List<User> users = this.userService.findAllUsers();

    return ResponseEntity.ok(UserMapper.toListDTO(users));
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable long id) {

    Optional<User> user = this.userService.findUserById(id);

    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(UserMapper.toDTO(user.get()));
  }

  @PostMapping("/users")
  public ResponseEntity<Object> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

    if (this.userService.existsUserByUsername(userRequestDTO.username())) {
      Map<String, String> response = new HashMap<>();
      response.put("message", "User with such a username already exists");

      return ResponseEntity.badRequest().body(response);
    }
    User user = new User();

    user.setUsername(userRequestDTO.username());
    user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
    user.setRoles(userRequestDTO.roles());

    User savedUser = this.userService.saveUser(user);

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri())
            .body(UserMapper.toDTO(savedUser));

  }

  @PutMapping("/users/{id}")
  public ResponseEntity<Object> updateUser(@Valid @PathVariable int id, @RequestBody UserRequestDTO userRequestDTO) {

    if (this.userService.existsUserByUsername(userRequestDTO.username())) {
      Map<String, String> response = new HashMap<>();
      response.put("message", "User with such a username already exists");

      return ResponseEntity.badRequest().body(response);
    }

    Optional<User> userFromDb = this.userService.findUserById(id);

    if (userFromDb.isPresent()) {

      User updatedUser = userFromDb.get();

      updatedUser.setUsername(userRequestDTO.username());
      updatedUser.setPassword(userRequestDTO.password());

      User u = this.userService.saveUser(updatedUser);

      return ResponseEntity.ok(this.userService.saveUser(updatedUser));
    }

    User savedUser = this.userService.saveUser(UserMapper.toUser(userRequestDTO));

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .replacePath("/api/users/{index}")
                            .buildAndExpand(savedUser.getId())
                            .toUri())
            .body(UserMapper.toDTO(savedUser));
  }

  @GetMapping("/users/search")
  public ResponseEntity<List<UserResponseDTO>> getUsersByUsername(@RequestParam String username) {
    List<User> users = this.userService.findUsersByUsername(username);

    if (users.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(UserMapper.toListDTO(users));
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {

    Optional<User> user = this.userService.findUserById(id);

    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    this.userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/users/pagination")
  public ResponseEntity<Page<User>> getUsersPage(@RequestParam int page, @RequestParam int size) {
    return ResponseEntity.ok(userService.findAllUsersPage(page, size));
  }
}

