package nechto.service;

import nechto.dto.UserDto;
import nechto.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto user);

    User findByLogin(String login);

    List<User> findAll();

    void deleteUser(Integer userId);
}
