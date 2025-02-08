package nechto.controller;

import lombok.RequiredArgsConstructor;
import nechto.dto.ResponseScoresDto;
import nechto.dto.RequestScoresDto;
import nechto.service.ScoresService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
public class ScoresController {

    private final ScoresService scoresService;

    @PostMapping("/")
    ResponseScoresDto saveScores(@RequestBody RequestScoresDto scores) {
        return scoresService.saveScores(scores);
    }

    @PatchMapping("/{game_id}")
    void countForAll(@PathVariable(name = "game_id") Integer gameId) {
        scoresService.countForAll(gameId);
    }
}
