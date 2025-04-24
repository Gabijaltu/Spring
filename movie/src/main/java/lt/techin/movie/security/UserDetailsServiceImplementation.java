package lt.techin.movie.security;


import lt.techin.movie.model.User;
import lt.techin.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

  private final UserService userService;

  @Autowired
  public UserDetailsServiceImplementation(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> foundUser = this.userService.findUserByUsername(username);

    if (foundUser.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }

    return foundUser.get();
  }
}
