package pl.org.napierala.budget.krowka.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.repository.UserRepository;

@Controller
@RequestMapping("/api")
public class UserController {

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	@Transactional
	public @ResponseBody List<Map<String, Object>> getUsers() {
		return buildModel(userRepository.findAll(), true, true);
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
				model.put("accounts", AccountController.buildModel(user.getAccounts(), false));
			}
			model.put("username", user.getUsername());
			if (withRoles) {
				model.put("roles", RoleController.buildModel(user.getRoles(),
						false, false, false, false));
			}
		}
		return model;
	}

	@Autowired
	private UserRepository userRepository;

}
