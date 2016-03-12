package pl.org.napierala.budget.krowka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.org.napierala.budget.krowka.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	public Account save(Account account);

	public Account findOne(Long accountId);

	public Iterable<Account> findAll();

	public long count();

	public void delete(Account account);

	public boolean exists(Long accountId);

}
