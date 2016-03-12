package pl.org.napierala.budget.krowka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.org.napierala.budget.krowka.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	public Person save(Person person);

	public Person findOne(Long personId);

	public Iterable<Person> findAll();

	public long count();

	public void delete(Person person);

	public boolean exists(Long personId);

}
