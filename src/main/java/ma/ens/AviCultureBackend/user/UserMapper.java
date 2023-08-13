package ma.ens.AviCultureBackend.user;

import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDto toUserDto(User user);
	User toUser(UserDto user);

}
