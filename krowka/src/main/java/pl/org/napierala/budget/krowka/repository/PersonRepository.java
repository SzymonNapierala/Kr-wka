package pl.org.napierala.budget.krowka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.org.napierala.budget.krowka.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	public Person save(Person person);

	Person findOne(Long personId);

	Iterable<Person> findAll();

	long count();

	void delete(Person person);

	boolean exists(Long personId);

}
