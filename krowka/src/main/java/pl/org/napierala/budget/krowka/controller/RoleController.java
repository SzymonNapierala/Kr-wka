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

	@RequestMapping(method = RequestMethod.GET, value = "/role/{id}")
	@Transactional
	public @ResponseBody Map<String, Object> getRole(@PathVariable(value = "id") Long id) {
		return buildModel(roleRepository.findOne(id), true, true, true, true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/roles")
	@Transactional
	public @ResponseBody List<Map<String, Object>> getRoles() {
		return buildModel(roleRepository.findAll(), true, true, true, true);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/role/{id}")
	@Transactional
	public @ResponseBody Map<String, Object> updateRole(
			@PathVariable(value = "id") Long id,
			@RequestBody Role role) {
		role.setId(id);
		role = roleRepository.save(role);
		return buildModel(role, true, true, true, true);
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
				model.put("childrenRoles", (role.getChildrenRoles() != null)
						? buildModel(role.getChildrenRoles(), false, false, false, false)
						: null);
			}
			if (withRolePermissions) {
				model.put("rolePermissions", (role.getRolePermissions() != null)
						? RolePermissionController.buildModel(role.getRolePermissions(), false)
						: null);
			}
			if (withUsers) {
				model.put("users", (role.getUsers() != null)
						? UserController.buildModel(role.getUsers(), false, false)
						: null);
			}
		}
		return model;
	}

	@Autowired
	private RoleRepository roleRepository;

}
