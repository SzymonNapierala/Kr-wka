package pl.org.napierala.budget.krowka.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.org.napierala.budget.krowka.model.Account;
import pl.org.napierala.budget.krowka.model.Person;
import pl.org.napierala.budget.krowka.repository.AccountRepository;
import pl.org.napierala.budget.krowka.repository.PersonRepository;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index(Model model) {
		//TODO
		Account account = new Account();
		account.setInitialValue(new BigDecimal(1000));
		accountRepository.save(account);
		Person person = new Person();
		person.setFirstName("Jan");
		person.setLastName("Kowalski");
		personRepository.save(person);
		return "index";
	}

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PersonRepository personRepository;

}
