package pl.org.napierala.budget.krowka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import pl.org.napierala.budget.krowka.filter.CsrfHeaderFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests().antMatchers("/resources/**", "/login").permitAll()
		 		.anyRequest().authenticated().and()
		 		.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
		 		.csrf().csrfTokenRepository(this.getCsrfTokenRepository());
		 http.formLogin().loginPage("/login").loginProcessingUrl("/login")
		 		.defaultSuccessUrl("/").failureUrl("/login?error=true")
		 		.usernameParameter("username").passwordParameter("password");
	}

	private CsrfTokenRepository getCsrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}
