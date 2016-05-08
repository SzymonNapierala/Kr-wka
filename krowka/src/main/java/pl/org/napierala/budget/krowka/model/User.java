package pl.org.napierala.budget.krowka.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.org.napierala.budget.krowka.validation.constraints.FieldMatch;

@Entity
@FieldMatch(value = {"password", "passwordRepeat"})
public class User extends Person {

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.setPasswordEncrypted(passwordEncoder.encode(password));
	}

	public String getPasswordRepeat() {
		return this.passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public String getPasswordEncrypted() {
		return this.passwordEncrypted;
	}

	public void setPasswordEncrypted(String passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@NotNull
	@Size(min = 1)
	@Column(name = "username", unique = true)
	private String username;

	@Transient
	private String password;

	@Transient
	private String passwordRepeat;

	@NotNull
	@Size(min = 1)
	@Column(name = "password_encrypted")
	private String passwordEncrypted;

	@ManyToMany
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

}
