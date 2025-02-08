package nechto.service;

import nechto.dto.ResponseGameDto;
import nechto.dto.RequestGameDto;

public interface GameService {
    ResponseGameDto save(RequestGameDto game);

    ResponseGameDto addUser(Integer gameId, Integer userId);

    void deleteUserFromGame(Integer gameId, Integer userId); 
}
