package pl.org.napierala.budget.krowka.rbac.permission.user;

import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.rbac.permission.Permission;

public class UsersDeletePermission implements Permission {

	private static final String NAME = "users_delete";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean hasAccess(Object object, User user) {
		return object instanceof User;
	}

}
