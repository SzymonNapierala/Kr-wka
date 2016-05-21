package pl.org.napierala.budget.krowka.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import pl.org.napierala.budget.krowka.model.User;

public interface UserService extends UserDetailsService {

	public User getCurrentUser();

}
