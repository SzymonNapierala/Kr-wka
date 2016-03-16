package pl.org.napierala.budget.krowka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.org.napierala.budget.krowka.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User save(User user);

	public User findOne(Long userId);

	public Iterable<User> findAll();

	public long count();

	public void delete(User user);

	public boolean exists(Long userId);

}
