package pl.org.napierala.budget.krowka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.org.napierala.budget.krowka.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	public Role save(Role role);

	public Role findOne(Long roleId);

	public Role findByName(String name);

	public Iterable<Role> findAll();

	public long count();

	public void delete(Role role);

	public boolean exists(Long roleId);

}
