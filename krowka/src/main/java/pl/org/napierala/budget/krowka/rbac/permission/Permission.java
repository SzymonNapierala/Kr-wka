package pl.org.napierala.budget.krowka.rbac.permission;

import pl.org.napierala.budget.krowka.model.User;

public interface Permission {

	public String getName();

	public boolean hasAccess(Object object, User user);

}
