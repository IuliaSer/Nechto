package nechto.service;

import lombok.RequiredArgsConstructor;
import nechto.dto.UserDto;
import nechto.entity.User;
import nechto.exception.UserAlreadyExistsException;
import nechto.mappers.UserMapper;
import nechto.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public void saveUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistsException("A user with this login already exists");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRole(userDto.getRole());
        userRepository.save(userMapper.convertToUser(userDto));
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByUsername(login);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}
