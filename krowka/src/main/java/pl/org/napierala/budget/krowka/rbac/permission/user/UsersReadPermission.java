package pl.org.napierala.budget.krowka.rbac.permission.user;

import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.rbac.permission.Permission;

public class UsersReadPermission implements Permission {

	private static final String NAME = "users_read";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean hasAccess(Object object, User user) {
		return object instanceof User;
	}

}
