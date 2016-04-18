package pl.org.napierala.budget.krowka.rbac.permission.user;

import pl.org.napierala.budget.krowka.rbac.permission.Permission;

public class UsersDeletePermission implements Permission {

	private static final String NAME = "users_delete";

	@Override
	public String getName() {
		return NAME;
	}

}
