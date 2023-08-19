package ma.ens.AviCultureBackend.user;

import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
	@Mapping(target = "password", constant = "[protected]")
	UserDto toUserDto(User user);
	User toUser(UserDto user);

}
