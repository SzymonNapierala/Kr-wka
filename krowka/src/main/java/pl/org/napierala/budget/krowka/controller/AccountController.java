package pl.org.napierala.budget.krowka.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.org.napierala.budget.krowka.model.Account;
import pl.org.napierala.budget.krowka.repository.AccountRepository;

@Controller
@RequestMapping("/api")
public class AccountController {

	@RequestMapping(method = RequestMethod.GET, value = "/accounts")
	@Transactional
	public @ResponseBody List<Map<String, Object>> getAccounts() {
		return buildModel(accountRepository.findAll(), true);
	}

	public static List<Map<String, Object>> buildModel(
			Iterable<Account> accounts, boolean withPerson) {
		List<Map<String, Object>> model = new LinkedList<>();
		for (Account account: accounts) {
			model.add(buildModel(account, withPerson));
		}
		return model;
	}

	public static Map<String, Object> buildModel(Account account, boolean withPerson) {
		Map<String, Object> model = null;
		if (account != null) {
			model = new HashMap<>();
			model.put("id", account.getId());
			model.put("initialValue", account.getInitialValue());
			if (withPerson) {
				model.put("person", PersonController.buildModel(account.getPerson(), false));
			}
		}
		return model;
	}

	@Autowired
	private AccountRepository accountRepository;

}
