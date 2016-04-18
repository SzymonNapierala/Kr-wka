package pl.org.napierala.budget.krowka.rbac.permission.user;

import pl.org.napierala.budget.krowka.rbac.permission.Permission;

public class UsersWritePermission implements Permission {

	private static final String NAME = "users_write";

	@Override
	public String getName() {
		return NAME;
	}

}
