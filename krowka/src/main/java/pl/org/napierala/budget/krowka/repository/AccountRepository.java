package pl.org.napierala.budget.krowka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.org.napierala.budget.krowka.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	public Account save(Account account);

	Account findOne(Long accountId);

	Iterable<Account> findAll();

	long count();

	void delete(Account account);

	boolean exists(Long accountId);

}
