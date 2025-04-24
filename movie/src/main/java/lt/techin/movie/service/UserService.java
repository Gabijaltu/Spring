package lt.techin.movie.service;

import lt.techin.movie.model.User;
import lt.techin.movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User saveUser(User user) {
    return this.userRepository.save(user);
  }

  public boolean existsUserByUsername(String username) {
    return this.userRepository.existsByUsername(username);
  }

  public Optional<User> findUserByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  public List<User> findUsersByUsername(String username) {
    return userRepository.findAllByUsername(username);
  }

  public List<User> findAllUsers() {
    return this.userRepository.findAll();
  }

  public Optional<User> findUserById(long id) {
    return this.userRepository.findById(id);
  }

  public void deleteUserById(long id) {
    this.userRepository.deleteById(id);
  }

  public Page<User> findAllUsersPage(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return userRepository.findAll(pageable);
  }
}
