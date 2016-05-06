package pl.org.napierala.budget.krowka.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.org.napierala.budget.krowka.model.Role;
import pl.org.napierala.budget.krowka.repository.RoleRepository;

@Controller
@RequestMapping("/api")
public class RoleController {

	@RequestMapping(method = RequestMethod.POST, value = "/role")
	@Transactional
	public @ResponseBody Map<String, Object> createRole(@RequestBody Role role) {
		role = roleRepository.save(role);
		return buildModel(role, true, true, true, true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/roles")
	@Transactional
	public @ResponseBody List<Map<String, Object>> getRoles() {
		return buildModel(roleRepository.findAll(), true, true, true, true);
	}

	public static List<Map<String, Object>> buildModel(Iterable<Role> roles,
			boolean withParentRole, boolean withChildrenRoles,
			boolean withRolePermissions, boolean withUsers) {
		List<Map<String, Object>> model = new LinkedList<>();
		for (Role role: roles) {
			model.add(buildModel(role, withParentRole, withChildrenRoles,
					withRolePermissions, withUsers));
		}
		return model;
	}

	public static Map<String, Object> buildModel(Role role, boolean withParentRole,
			boolean withChildrenRoles, boolean withRolePermissions, boolean withUsers) {
		Map<String, Object> model = null;
		if (role != null) {
			model = new HashMap<>();
			model.put("id", role.getId());
			model.put("name", role.getName());
			model.put("forGuest", role.isForGuest());
			if (withParentRole) {
				model.put("parentRole", buildModel(role.getParentRole(),
						false, false, false, false));
			}
			if (withChildrenRoles) {
				model.put("childrenRoles", buildModel(role.getChildrenRoles(),
						false, false, false, false));
			}
			if (withRolePermissions) {
				model.put("rolePermissions",
						RolePermissionController.buildModel(role.getRolePermissions(), false));
			}
			if (withUsers) {
				model.put("users", UserController.buildModel(role.getUsers(), false, false));
			}
		}
		return model;
	}

	@Autowired
	private RoleRepository roleRepository;

}
