package nechto.service;

import lombok.RequiredArgsConstructor;
import nechto.dto.GameDto;
import nechto.entity.Game;
import nechto.entity.User;
import nechto.mappers.GameMapper;
import nechto.repository.GameRepository;
import nechto.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    private final GameMapper gameMapper;

    @Override
    public GameDto save(GameDto game) {
        return gameMapper.convertToGameDto(gameRepository.save(gameMapper.convertToGame(game)));
    }

    @Override
    public GameDto addUser(Integer gameId, Integer userId) {
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
