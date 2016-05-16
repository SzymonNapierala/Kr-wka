package pl.org.napierala.budget.krowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.org.napierala.budget.krowka.model.User;
import pl.org.napierala.budget.krowka.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPasswordEncrypted(),
					user.getRoles());
		}
		throw new UsernameNotFoundException(username);
	}

	@Autowired
	private UserRepository userRepository;

}
