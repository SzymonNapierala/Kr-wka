package pl.org.napierala.budget.krowka.rbac.permission.user;

import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.rbac.permission.Permission;

public class UserOwnDeletePermission implements Permission {

	private static final String NAME = "user_own_delete";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean hasAccess(Object object, User user) {
		if (object instanceof User) {
			User u = (User) object;
			return ((u != null) && (u.getId() != null)
					&& (user != null) && (user.getId() != null)
					&& (u.getId().equals(user.getId())));
		}
		return false;
	}

}
