package ma.ens.AviCultureBackend.user.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "cin", nullable = false)
	private String CIN;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@ManyToMany(fetch = EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"),
			uniqueConstraints = @UniqueConstraint(name = "UNIQUE_USER_ROLE",
					columnNames = {"user_id", "role_id"}))
	private Collection<UserRole> roles = new ArrayList<>();

	@Column(name = "birth_date")
	private LocalDate birthDate;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_loggedIn")
	private boolean isLoggedIn;

	@Column(name = "salary")
	private Float salary;

	@Column(name = "function_name")
	private String functionName;

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "soft_deleted_date")
	private LocalDateTime softDeletedDate;

	@OneToMany(mappedBy = "user", fetch = LAZY)
	private Set<UserSession> userSessions = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
		this.getRoles().forEach(role ->
				authority.add(new SimpleGrantedAuthority(role.getName())));
		return authority;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isActive;
	}

	@Override
	public boolean isEnabled() {
		return this.isActive();
	}

}
