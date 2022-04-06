package code.sakarias.userservice.service;

import code.sakarias.userservice.role.Role;
import code.sakarias.userservice.user.AppUser;
import java.util.List;

public interface UserService {
  AppUser saveUser(AppUser user);

  Role saveRole(Role role);

  void addRoleToUser(String username, String roleName);

  AppUser getUser(String username);
// If many users should return a page of users, 1-19 on page 1 20-39 on page to.
  List<AppUser> getUsers();
}
