package code.sakarias.userservice.user;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

import code.sakarias.userservice.role.Role;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  private String name;
  private String username;
  private String password;
  private boolean active = true;
  @ManyToMany(fetch = EAGER)
  private Collection<Role> roles = new ArrayList<>();

}
