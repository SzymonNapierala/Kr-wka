package pl.org.napierala.budget.krowka.rbac.permission.user;

import pl.org.napierala.budget.krowka.rbac.permission.Permission;

public class UsersReadPermission implements Permission {

	private static final String NAME = "users_read";

	@Override
	public String getName() {
		return NAME;
	}

}
