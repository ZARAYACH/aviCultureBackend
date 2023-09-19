package ma.ens.AviCultureBackend.user.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.Jwts.EmailPasswordModal;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.exeption.UnauthenticatedException;
import ma.ens.AviCultureBackend.user.UserMapper;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserDto;
import ma.ens.AviCultureBackend.user.modal.UserRoleDto;
import ma.ens.AviCultureBackend.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    private List<UserDto> getAllUsers(){
        return userMapper.toUserDtos(userService.getUsers());
    }

    @GetMapping("/roles")
    private List<UserRoleDto> getAllRoles(){
        return userMapper.toRoleDtos(userService.getAllUserRoles());
    }


    @PostMapping(path = "/add")
    public UserDto AddUser(@RequestBody @Validated UserDto userDto) throws BadRequestExeption {
        return userMapper.toUserDto(userService.addUser(userDto));
    }


    @GetMapping(path = "/info")
    public UserDto getUserInfo() throws NotFoundException {
        User user = userService.getLoggedInUser();
        return userMapper.toUserDto(user);
    }

    @PutMapping(path = "/user/changeInfo")
    public UserDto changeUserInfo(@RequestBody UserDto userDto) throws UnauthenticatedException, NotFoundException {
        User user = userService.getLoggedInUser();
        return userMapper.toUserDto(userService.changeAndSaveUserInfo(user, userDto));
    }

    @DeleteMapping(path = "/user/deleteAccount")
    public Map<String, Boolean> deleteAccount(Authentication authentication) throws UnauthenticatedException, NotFoundException {
        User user = userService.getLoggedInUser();
        return Collections.singletonMap("deleted", userService.deleteUser(user));
    }
    @DeleteMapping(path = "/user/{userId}/softDelete")
    public Map<String, Boolean> deleteUser(@PathVariable(name = "userId") Long userId) throws UnauthenticatedException, NotFoundException {
        User user = userService.getUserById(userId);
        return Collections.singletonMap("deleted", userService.deleteUser(user));
    }

//	@PostMapping(path = "/logout")
//	public ResponseEntity<?> userLougout() throws UnauthenticatedException, NotFoundException {
//		User user = userService.getLoggedInUser();
//		return userService.logout(user);
//	}

//	//Admin methods
//	@GetMapping(path = "/admin/getUsers")
//	public ResponseEntity<?> getUsers(Authentication authentication) {
//		return userService.getUsers(authentication);
//	}
//
//	@PostMapping(path = "/admin/suspendUser")
//	public ResponseEntity<?> suspendUser(Authentication authentication, @RequestBody User user) {
//		return userService.suspendUser(authentication, user);
//	}
//
//	@PostMapping(path = "/admin/unSuspendUser")
//	public ResponseEntity<?> unSuspendUser(Authentication authentication, @RequestBody User user) {
//		return userService.unSuspendUser(authentication, user);
//	}
//
//	@DeleteMapping(path = "/admin/deleteUser")
//	public ResponseEntity<?> deleteUser(Authentication authentication, @RequestBody User toBeDel) {
//		return userService.deleteUser(authentication, toBeDel);
//	}
}
