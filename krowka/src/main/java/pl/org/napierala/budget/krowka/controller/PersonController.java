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

import pl.org.napierala.budget.krowka.model.Person;
import pl.org.napierala.budget.krowka.repository.PersonRepository;

@Controller
@RequestMapping("/api")
public class PersonController {

	@RequestMapping(method = RequestMethod.GET, value = "/people")
	@Transactional
	public @ResponseBody List<Map<String, Object>> getPeople() {
		return buildModel(personRepository.findAll(), true);
	}

	public static List<Map<String, Object>> buildModel(
			Iterable<Person> persons, boolean withAccounts) {
		List<Map<String, Object>> model = new LinkedList<>();
		for (Person person: persons) {
			model.add(buildModel(person, withAccounts));
		}
		return model;
	}

	public static Map<String, Object> buildModel(Person person, boolean withAccounts) {
		Map<String, Object> model = null;
		if (person != null) {
			model = new HashMap<>();
			model.put("id", person.getId());
			model.put("firstName", person.getFirstName());
			model.put("lastName", person.getLastName());
			if (withAccounts) {
				model.put("accounts", AccountController.buildModel(person.getAccounts(), false));
			}
		}
		return model;
	}

	@Autowired
	private PersonRepository personRepository;

}
