package pl.org.napierala.budget.krowka.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.org.napierala.budget.krowka.model.Role;
import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.repository.RoleRepository;
import pl.org.napierala.budget.krowka.repository.UserRepository;

@Service
public class BootstrapService implements InitializingBean {

	private String ROLE_ADMINISTRATOR_NAME = "administrator";
	private String ROLE_GUEST_NAME = "guest";
	private String ROLE_USER_NAME = "user";

	private String USER_ADMIN_USERNAME = "admin";
	private String USER_ADMIN_PASSWORD = "admin";

	@Override
	public void afterPropertiesSet() throws Exception {
		this.createDefaultRoles();
		this.createDefaultUsers();
	}

	private void createDefaultRoles() {
		createRole(ROLE_GUEST_NAME, true, null);
		createRole(ROLE_USER_NAME, false, null);
		createRole(ROLE_ADMINISTRATOR_NAME, false, roleRepository.findByName(ROLE_USER_NAME));
	}

	private Role createRole(String name, boolean forGuest, Role parentRole) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			role.setForGuest(forGuest);
			role.setParentRole(parentRole);
			role = roleRepository.save(role);
		}
		return role;
	}

	private void createDefaultUsers() {
		User user = userRepository.findByUsername(USER_ADMIN_USERNAME);
		if (user == null) {
			user = new User();
			user.setUsername(USER_ADMIN_USERNAME);
			user.setPassword(USER_ADMIN_PASSWORD);
			user.setPasswordRepeat(USER_ADMIN_PASSWORD);
			List<Role> roles = new LinkedList<>();
			roles.add(roleRepository.findByName(ROLE_ADMINISTRATOR_NAME));
			user.setRoles(roles);
			user = userRepository.save(user);
		}
	}

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

}
