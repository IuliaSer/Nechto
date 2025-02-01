package nechto.controller;

import lombok.RequiredArgsConstructor;
import nechto.dto.GameDto;
import nechto.service.GameService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping
    GameDto save(@RequestBody GameDto game) {
        return gameService.save(game);
    }

    @PatchMapping("/{game_id}/user/{user_id}")
    GameDto addUserToGame(@PathVariable(name = "game_id") Integer gameId, @PathVariable(name = "user_id") Integer userId) {
        return gameService.addUser(gameId, userId);
    }

    @DeleteMapping("/{game_id}/user/{user_id}")
    void deleteUserFromGame(@PathVariable(name = "game_id") Integer gameId,
                            @PathVariable(name = "user_id") Integer userId) {
        gameService.deleteUserFromGame(gameId, userId);
    }
}
