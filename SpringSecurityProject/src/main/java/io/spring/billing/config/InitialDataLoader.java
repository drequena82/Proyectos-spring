package io.spring.billing.config;

import java.util.Arrays;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.spring.billing.entities.Role;
import io.spring.billing.entities.User;
import io.spring.billing.repositories.RoleRepository;
import io.spring.billing.repositories.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  boolean alreadySetup = false;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (alreadySetup) {
      return;
    }

    Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
    Role supervisorRole = createRoleIfNotFound("ROLE_SUPERVISOR");
    Role userRole = createRoleIfNotFound("ROLE_USER");

    createUserIfNotFound("admin1", "secret1", adminRole, supervisorRole, userRole);
    createUserIfNotFound("supervisor1", "secret1", supervisorRole, userRole);
    createUserIfNotFound("user1", "secret1", userRole);

    alreadySetup = true;
  }


  @Transactional
  Role createRoleIfNotFound(String name) {
    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role();
      role.setName(name);
      roleRepository.save(role);
    }
    return role;
  }

  @Transactional
  void createUserIfNotFound(String username, String password, Role... roles) {
    User user = new User();
    user.setUserName(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setRoles(new HashSet<>(Arrays.asList(roles)));
    userRepository.save(user);
  }
}