package pl.org.napierala.budget.krowka.rbac.permission;

import pl.org.napierala.budget.krowka.rbac.permission.user.UserOwnDeletePermission;
import pl.org.napierala.budget.krowka.rbac.permission.user.UserOwnReadPermission;
import pl.org.napierala.budget.krowka.rbac.permission.user.UserOwnWritePermission;
import pl.org.napierala.budget.krowka.rbac.permission.user.UsersDeletePermission;
import pl.org.napierala.budget.krowka.rbac.permission.user.UsersReadPermission;
import pl.org.napierala.budget.krowka.rbac.permission.user.UsersWritePermission;

public enum PermissionConst {

	USER_OWN_DELETE(new UserOwnDeletePermission()),
	USER_OWN_READ(new UserOwnReadPermission()),
	USER_OWN_WRITE(new UserOwnWritePermission()),
	USERS_DELETE(new UsersDeletePermission()),
	USERS_READ(new UsersReadPermission()),
	USERS_WRITE(new UsersWritePermission());

	private PermissionConst(Permission permission) {
		this.permission = permission;
	}

	public Permission getPermission() {
		return this.permission;
	}

	private Permission permission;

}
