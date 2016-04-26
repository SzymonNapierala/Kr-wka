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

import pl.org.napierala.budget.krowka.model.RolePermission;
import pl.org.napierala.budget.krowka.repository.RolePermissionRepository;

@Controller
@RequestMapping("/api")
public class RolePermissionController {

	@RequestMapping(method = RequestMethod.GET, value = "/rolePermissions")
	@Transactional
	public @ResponseBody List<Map<String, Object>> getRolePermissions() {
		return buildModel(rolePermissionRepository.findAll(), true);
	}

	public static List<Map<String, Object>> buildModel(
			Iterable<RolePermission> rolePermissions, boolean withRole) {
		List<Map<String, Object>> model = new LinkedList<>();
		for (RolePermission rolePermission: rolePermissions) {
			model.add(buildModel(rolePermission, withRole));
		}
		return model;
	}

	public static Map<String, Object> buildModel(RolePermission rolePermission, boolean withRole) {
		Map<String, Object> model = new HashMap<>();
		model.put("id", rolePermission.getId());
		model.put("permission", rolePermission.getPermission());
		if (withRole) {
			model.put("role", RoleController.buildModel(
					rolePermission.getRole(), false, false, false, false));
		}
		return model;
	}

	@Autowired
	private RolePermissionRepository rolePermissionRepository;

}
