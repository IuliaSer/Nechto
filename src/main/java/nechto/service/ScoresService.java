package nechto.service;

import nechto.dto.ResponseScoresDto;
import nechto.dto.RequestScoresDto;

public interface ScoresService {
    ResponseScoresDto saveScores(RequestScoresDto scores);

    void countForAll(Integer gameId);
}
