package nechto.mappers;

import nechto.dto.UserDto;
import nechto.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto convertToUserDto(User User);

    User convertToUser(UserDto userDto);

    List<UserDto> convertToListOfUserDto(List<User> users);
}
