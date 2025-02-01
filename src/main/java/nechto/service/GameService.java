package nechto.service;

import nechto.dto.GameDto;

public interface GameService {
    GameDto save(GameDto game);

    GameDto addUser(Integer gameId, Integer userId);

    void deleteUserFromGame(Integer gameId, Integer userId);
}
