package code.sakarias.userservice.service;

import code.sakarias.userservice.repo.RoleRepo;
import code.sakarias.userservice.repo.UserRepo;
import code.sakarias.userservice.role.Role;
import code.sakarias.userservice.user.AppUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser user = userRepo.findByUsername(username);
    if (user == null) {
      log.error("User was not found in the database");
      throw new UsernameNotFoundException("User was not found in the database");
    } else {
      log.info("User found in the database: {}", username);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));});
    return new User(user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public AppUser saveUser(AppUser user) {
    log.info("Saving new user {} to the database", user.getName());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepo.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    log.info("Saving new role {} to the database", role.getName());
    return roleRepo.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    log.info("Adding role {} to user {}", roleName, username);
    AppUser user = userRepo.findByUsername(username);
    Role role = roleRepo.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public AppUser getUser(String username) {
    log.info("Fetching user {}", username);
    return userRepo.findByUsername(username);
  }

  @Override
  public List<AppUser> getUsers() {
    log.info("Fetching all users");
    return userRepo.findAll();
  }
}
