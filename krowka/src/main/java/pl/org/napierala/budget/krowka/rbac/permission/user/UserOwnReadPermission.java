package pl.org.napierala.budget.krowka.rbac.permission.user;

import pl.org.napierala.budget.krowka.rbac.permission.Permission;

public class UserOwnReadPermission implements Permission {

	private static final String NAME = "user_own_read";

	@Override
	public String getName() {
		return NAME;
	}

}
