package pl.org.napierala.budget.krowka.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.repository.UserRepository;

@Controller
@RequestMapping("/api")
public class UserController {

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	@Transactional
	public @ResponseBody Map<String, Object> createUser(@RequestBody User user) {
		user = userRepository.save(user);
		return buildModel(user, true, true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	@Transactional
	public @ResponseBody Map<String, Object> getUser(@PathVariable(value = "id") Long id) {
		return buildModel(userRepository.findOne(id), true, true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	@Transactional
	public @ResponseBody List<Map<String, Object>> getUsers() {
		return buildModel(userRepository.findAll(), true, true);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
	@Transactional
	public @ResponseBody Map<String, Object> updateUser(
			@PathVariable(value = "id") Long id,
			@RequestBody User user) {
		user.setId(id);
		user = userRepository.save(user);
		return buildModel(user, true, true);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	@Transactional
	public @ResponseBody Map<String, Object> deleteUser(
			@PathVariable(value = "id") Long id) {
		User user = userRepository.findOne(id);
		userRepository.delete(user);
		return buildModel(user, true, true);
	}

	public static List<Map<String, Object>> buildModel(
			Iterable<User> users, boolean withAccounts, boolean withRoles) {
		List<Map<String, Object>> model = new LinkedList<>();
		for (User user: users) {
			model.add(buildModel(user, withAccounts, withRoles));
		}
		return model;
	}

	public static Map<String, Object> buildModel(User user,
			boolean withAccounts, boolean withRoles) {
		Map<String, Object> model = null;
		if (user != null) {
			model = new HashMap<>();
			model.put("id", user.getId());
			model.put("firstName", user.getFirstName());
			model.put("lastName", user.getLastName());
			if (withAccounts) {
				model.put("accounts", (user.getAccounts() != null)
						? AccountController.buildModel(user.getAccounts(), false)
						: null);
			}
			model.put("username", user.getUsername());
			if (withRoles) {
				model.put("roles", (user.getRoles() != null)
						? RoleController.buildModel(user.getRoles(), false, false, false, false)
						: null);
			}
		}
		return model;
	}

	@Autowired
	private UserRepository userRepository;

}
