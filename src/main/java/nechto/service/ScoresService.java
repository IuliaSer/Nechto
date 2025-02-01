package nechto.service;

import nechto.dto.GameDto;
import nechto.dto.ScoresDto;
import nechto.entity.Game;
import nechto.entity.Scores;

public interface ScoresService {
    ScoresDto saveScores(ScoresDto scores);

    void countForAll(Integer gameId);
}
