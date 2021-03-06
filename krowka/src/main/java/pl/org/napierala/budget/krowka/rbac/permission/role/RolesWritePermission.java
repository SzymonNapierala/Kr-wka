package pl.org.napierala.budget.krowka.rbac.permission.role;

import pl.org.napierala.budget.krowka.model.Role;
import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.rbac.permission.Permission;

public class RolesWritePermission implements Permission {

	private static final String NAME = "roles_write";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean hasAccess(Object object, User user) {
		return object instanceof Role;
	}

}
