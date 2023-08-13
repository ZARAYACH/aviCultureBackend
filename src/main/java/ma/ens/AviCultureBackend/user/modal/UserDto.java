package ma.ens.AviCultureBackend.user.modal;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

public record UserDto(
		Long id,
		String cin,
		String firstName,
		String lastName,
		String email,
		User.Gender gender,
		Collection<UserRole> roles,
		LocalDate birthDate,
		boolean isActive,
		boolean isLoggedIn,
		String phoneNumber,
		String imagePath,
		Float salary,
		String function
		){}
