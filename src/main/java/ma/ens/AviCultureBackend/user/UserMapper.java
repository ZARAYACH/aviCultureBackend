package ma.ens.AviCultureBackend.user;

import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserDto;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import ma.ens.AviCultureBackend.user.modal.UserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
	@Mapping(target = "password", constant = "[protected]")
	UserDto toUserDto(User user);

	List<UserDto> toUserDtos(List<User> users);
	User toUser(UserDto user);

	List<UserRoleDto> toRoleDtos(List<UserRole> allUserRoles);
}
