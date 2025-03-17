package nechto.service;

import lombok.RequiredArgsConstructor;
import nechto.dto.request.FullUserDto;
import nechto.dto.request.RequestUserDto;
import nechto.dto.response.ResponseUserDto;
import nechto.entity.User;
import nechto.exception.UserAlreadyExistsException;
import nechto.mappers.UserMapper;
import nechto.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static nechto.enums.Authority.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public ResponseUserDto saveUser(RequestUserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistsException("A user with this login already exists");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setAuthority(userDto.getAuthority());
        return userMapper.convertToResponseUserDto(userRepository.save(userMapper.convertToUser(userDto)));
    }

    @Override
    public ResponseUserDto findByUsername(String username) {
        return userMapper.convertToResponseUserDto(userRepository.findByUsername(username));
    }

    @Override
    public List<ResponseUserDto> findAll() {
        return userMapper.convertToListOfResponseUserDto(userRepository.findAll());
    }

    @Override
    public List<ResponseUserDto> findUsersOrderedByGamesAmount() {
        return userMapper.convertToListOfResponseUserDto(userRepository.findAllOrderedByGameAmount());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void makeAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", userId)));
        user.setAuthority(ROLE_ADMIN);
        userRepository.save(user);
    }

    @Override
    public ResponseUserDto updateUser(FullUserDto userDto) {
        User user = User.builder()
                        .id(userDto.getId())
                        .name(userDto.getName())
                        .username(userDto.getUsername())
                        .authority(userDto.getAuthority())
                        .build();
        return userMapper.convertToResponseUserDto(userRepository.save(user));
    }
}
