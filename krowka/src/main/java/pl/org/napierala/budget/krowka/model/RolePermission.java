package pl.org.napierala.budget.krowka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import pl.org.napierala.budget.krowka.rbac.permission.Permission;
import pl.org.napierala.budget.krowka.rbac.permission.PermissionConst;

@Entity
public class RolePermission {

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permission getPermission() {
		return (this.permissionConst != null) ? this.permissionConst.getPermission() : null;
	}

	public PermissionConst getPermissionConst() {
		return this.permissionConst;
	}

	public void setPermissionConst(PermissionConst permissionConst) {
		this.permissionConst = permissionConst;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "permission")
	private PermissionConst permissionConst;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

}
