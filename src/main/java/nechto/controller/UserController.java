package nechto.controller;

import lombok.RequiredArgsConstructor;
import nechto.dto.UserDto;
import nechto.entity.User;
import nechto.mappers.UserMapper;
import nechto.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    List<UserDto> getUsers() {
        List<User> users = userService.findAll();
        return userMapper.convertToListOfUserDto(users);
    }

    @PostMapping
    void saveUser(@RequestBody UserDto user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/{user_id}")
    void deleteUser(@PathVariable(name = "user_id") Integer userId) {
        userService.deleteUser(userId);
    }
}
