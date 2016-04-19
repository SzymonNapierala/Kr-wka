package pl.org.napierala.budget.krowka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.org.napierala.budget.krowka.model.Role;
import pl.org.napierala.budget.krowka.model.RolePermission;
import pl.org.napierala.budget.krowka.rbac.permission.PermissionConst;

@Repository
public interface RolePermissionRepository extends CrudRepository<RolePermission, Long> {

	public RolePermission save(RolePermission rolePermission);

	public RolePermission findOne(Long rolePermissionId);

	public RolePermission findByRoleAndPermissionConst(Role role, PermissionConst permissionConst);

	public Iterable<RolePermission> findAll();

	public long count();

	public void delete(RolePermission rolePermission);

	public boolean exists(Long rolePermissionId);

}
