package code.sakarias.userservice.repo;

import code.sakarias.userservice.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
  AppUser findByUsername(String username);
}
