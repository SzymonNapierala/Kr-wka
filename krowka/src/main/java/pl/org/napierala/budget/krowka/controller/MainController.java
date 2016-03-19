package pl.org.napierala.budget.krowka.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.org.napierala.budget.krowka.model.Account;
import pl.org.napierala.budget.krowka.model.Person;
import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.repository.AccountRepository;
import pl.org.napierala.budget.krowka.repository.PersonRepository;
import pl.org.napierala.budget.krowka.repository.UserRepository;

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
		if (userRepository.findByUsername("jan.kowalski") == null) {
			User user = new User();
			user.setFirstName("Jan");
			user.setLastName("Kowalski");
			user.setUsername("jan.kowalski");
			user.setPassword("jan.kowalski");
			user.setPasswordRepeat("jan.kowalski");
			userRepository.save(user);
		}
		return "index";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
			securityContextLogoutHandler.logout(request, response, authentication);
		}
		return "redirect:/";
	}

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	UserRepository userRepository;

}
