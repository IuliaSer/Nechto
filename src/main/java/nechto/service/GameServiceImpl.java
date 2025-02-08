package nechto.service;

import lombok.RequiredArgsConstructor;
import nechto.dto.ResponseGameDto;
import nechto.dto.RequestGameDto;
import nechto.entity.Game;
import nechto.entity.User;
import nechto.mappers.GameMapper;
import nechto.repository.GameRepository;
import nechto.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    private final GameMapper gameMapper;

    @Override
    public ResponseGameDto save(RequestGameDto gameDto) {
        List<User> users = new ArrayList<>();
        for (Integer userId: gameDto.getUserIds()) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", userId)));
            users.add(user);
        }
        Game game = new Game(gameDto.getId(), gameDto.getDate(), users);
        return gameMapper.convertToGameDto(gameRepository.save(game));
    }

    @Override
    public ResponseGameDto addUser(Integer gameId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", userId)));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Game with id %s not found", gameId)));
        game.getUsers().add(user);
        return gameMapper.convertToGameDto(gameRepository.save(game));
    }

    @Override
    public void deleteUserFromGame(Integer gameId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", userId)));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Game with id %s not found", gameId)));
        game.getUsers().remove(user);
        gameRepository.save(game);
    }
}
