package pl.org.napierala.budget.krowka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.org.napierala.budget.krowka.repository.AccountRepository;
import pl.org.napierala.budget.krowka.repository.PersonRepository;
import pl.org.napierala.budget.krowka.repository.UserRepository;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index(Model model) {
		//TODO
		return "index";
	}

	@RequestMapping("/configuration")
	public String configuration(Model model,
			@RequestParam(value = "page", required = false) String page) {
		return (page == null) ? "configuration" : "configuration/" + page;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (error != null) {
			model.addObject("error", "validation.username_or_password.invalid");
		}
		model.setViewName("login");
		if ((authentication != null)
				&& !(authentication instanceof AnonymousAuthenticationToken)
				&& authentication.isAuthenticated()) {
			model.setViewName("redirect:/");
		}
		return model;
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
