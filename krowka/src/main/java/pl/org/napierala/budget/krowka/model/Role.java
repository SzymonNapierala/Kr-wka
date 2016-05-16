package pl.org.napierala.budget.krowka.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getAuthority() {
		return "ROLE_" + this.getName().toUpperCase();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isForGuest() {
		return forGuest;
	}

	public void setForGuest(boolean forGuest) {
		this.forGuest = forGuest;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Role getParentRole() {
		return this.parentRole;
	}

	public void setParentRole(Role parentRole) {
		this.parentRole = parentRole;
	}

	public List<Role> getChildrenRoles() {
		return childrenRoles;
	}

	public void setChildrenRoles(List<Role> childrenRoles) {
		this.childrenRoles = childrenRoles;
	}

	public List<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "for_guest")
	private boolean forGuest;

	@ManyToOne
	@JoinColumn(name = "parent_role_id")
	private Role parentRole;

	@OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "parentRole")
	private List<Role> childrenRoles;

	@OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "role")
	private List<RolePermission> rolePermissions;

	@ManyToMany
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

}
