package ma.ens.AviCultureBackend.user.service;


import lombok.AllArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.exeption.UnauthenticatedException;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserDto;
import ma.ens.AviCultureBackend.user.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepo userRepo;

	public User getLoggedInUser() throws NotFoundException, UnauthenticatedException {
		if (SecurityContextHolder.getContext().getAuthentication() == null ||
				!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			throw new UnauthenticatedException();
		}
		// Check if we are authenticated
		String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return userRepo.findUserByEmail(email)
				.orElseThrow(NotFoundException::new);
	}

	public User changeAndSaveUserInfo(User user, UserDto userDto) {
		if (user == null) {
			return null;
		}
		user.setBirthDate(userDto.birthDate());
		user.setFunction(userDto.function());
		user.setSalary(userDto.salary());
		user.setFirstName(userDto.firstName());
		user.setLastName(userDto.lastName());
		user.setGender(userDto.gender());
		user.setPhoneNumber(userDto.phoneNumber());
		return userRepo.save(user);
	}

	public boolean deleteUser(User user) {
		if (user == null) {
			return false;
		}
		user.setSoftDeletedDate(LocalDateTime.now());
		userRepo.save(user);
		return true;

	}

//	public ResponseEntity<?> getUsers(Authentication authentication) {
//		String email = authentication.getPrincipal().toString();
//		User admin = userRepo.findUserByEmail(email);
//		if (admin != null) {
//			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userRepo.getAllUsers());
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//
//	}
//
//	public ResponseEntity<?> suspendUser(Authentication authentication, User userTemp) {
//		String email = authentication.getPrincipal().toString();
//		User admin = userRepo.findUserByEmail(email);
//		User user = userRepo.getById(userTemp.getId());
//		if (user.isActive()) {
//			userRepo.suspendUser(user.getId());
//			Map<String, String> succes = new HashMap<>();
//			succes.put("success", "the user :" + user.getEmail() + "is suspended with success");
//			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(succes);
//		} else {
//			Map<String, String> error = new HashMap<>();
//			error.put("error", "user" + user.getEmail() + "already suspended");
//			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//		}
//	}
//
//	public ResponseEntity<?> unSuspendUser(Authentication authentication, User userTemp) {
//		String email = authentication.getPrincipal().toString();
//		User admin = userRepo.findUserByEmail(email);
//		User user = userRepo.getById(userTemp.getId());
//		if (user.isActive() == true) {
//			userRepo.unSuspendUser(user.getId());
//			Map<String, String> succes = new HashMap<>();
//			succes.put("success", "the user :" + user.getEmail() + "is activated with success");
//			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(succes);
//		} else {
//			Map<String, String> error = new HashMap<>();
//			error.put("error", "user" + user.getEmail() + "already active");
//			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//		}
//
//
//	}
//
//	public ResponseEntity<?> deleteUser(Authentication authentication, User toBeDel) {
//		String email = authentication.getPrincipal().toString();
//		User admin = userRepo.findUserByEmail(email);
//		if (userRepo.existsById(toBeDel.getId())) {
//			User toBeDelete = userRepo.getById(toBeDel.getId());
//			userRepo.delete(toBeDelete);
//		} else {
//			Map<String, String> error = new HashMap<>();
//			error.put("error", "user" + toBeDel.getId() + "doesn't exist");
//			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//		}
//		if (userRepo.existsById(toBeDel.getId())) {
//			Map<String, String> error = new HashMap<>();
//			error.put("error", "something went wrong");
//			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//		} else {
//			Map<String, String> success = new HashMap<>();
//			success.put("success", "the user with id =" + toBeDel.getId() + " was deleted with success");
//			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(success);
//		}
//	}
//
//	//TODO:to be compeleted later on
//	public ResponseEntity<?> UserSignUp(User user) {
//		if (userRepo.existsByEmail(user.getEmail()) == null && validEmail(user)) {
//			if (user.getBirthDate().getYear() < LocalDate.now().getYear() && user.getBirthDate() != null && user.getBirthDate().getYear() > 1900) {
//				if (userRepo.existsByPhoneNumber(user.getPhoneNumber()) == null && user.getPhoneNumber().length() == 10 && isNumeric(user.getPhoneNumber())) {
//					if (user.getUserCredentials() != null) {
//						if (userCredService.cheekStrongestOfPassword(user.getUserCredentials())) {
//							String encodedPass = passwordEncoder.encode(user.getUserCredentials().getPassword());
//							user.getUserCredentials().setEmail(user.getEmail());
//							user.getUserCredentials().setPassword(encodedPass);
//							user.setActive(false);
//							user.getUserCredentials().setActive(false);
//							user.setRoles(userRoleAuth$Repo.getUserRoleAuthByName(String.valueOf(UserRoles.CUSTOMER)));
//							UserCredentials userCredentials = userCredRepo.save(user.getUserCredentials());
//							user.setUserCredentials(userCredentials);
//							user.getUserCredentials().setVerficationToken(UUID.randomUUID().toString());
//							userRepo.save(user);
//							String link = "http://localhost:8081/api/v1/verifyAccount?token=" + user.getUserCredentials().getVerficationToken() + "&email=" + user.getEmail();
//
//							Thread newThread = new Thread(() -> {
//								mailSender.SendHtmlEmail(user.getEmail(),
//										"medrassachanuwu@gmail.com",
//										"verified your account",
//										"<h1>to veified your account</h1>" +
//												"<p>click <a href=" + link + ">HERE</a></p>");
//
//							});
//							newThread.start();
//
//							Map<String, String> success = new HashMap<>();
//							success.put("success", "you account was successfully created,to complete shopping you have to activate your account");
//							return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(success);
//
//						} else {
//							Map<String, String> error = new HashMap<>();
//							error.put("error", "the provided password should contain a number and lower and uper case letter and a charachter and tength betewenn 8 and 50");
//							return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//						}
//					} else {
//						Map<String, String> error = new HashMap<>();
//						error.put("error", "please add a credientiel");
//						return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//					}
//
//				} else {
//					Map<String, String> error = new HashMap<>();
//					error.put("error", "this phone number is already exists or its invalid number");
//					return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//				}
//			} else {
//				Map<String, String> error = new HashMap<>();
//				error.put("error", "invalid birth date");
//				return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//			}
//
//		} else {
//			Map<String, String> error = new HashMap<>();
//			error.put("error", "this email is already exists or it's not respecting email format");
//			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(error);
//		}
//	}
//
//	public ResponseEntity<?> logout(Authentication authentication) {
//
//		User user = userRepo.findUserByEmail(authentication.getPrincipal().toString());
//		if (user != null) {
//			user.setLoggedIn(false);
//			List<Logs> logs = logsRepo.findAllByUserWhereReferechTokenIsNotNull(user);
//			for (Logs log : logs) {
//				log.setLogoutTime(LocalDateTime.now());
//				log.setRefreshToken(null);
//			}
//			userRepo.save(user);
//			logsRepo.saveAll(logs);
//			return ResponseEntity.ok().build();
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//
//	}
//
//
//
//	public boolean isNumeric(String strNum) {
//		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
//		if (strNum == null) {
//			return false;
//		}
//		return pattern.matcher(strNum).matches();
//	}
}
