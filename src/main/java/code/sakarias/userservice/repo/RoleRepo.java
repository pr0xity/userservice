package code.sakarias.userservice.repo;

import code.sakarias.userservice.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
