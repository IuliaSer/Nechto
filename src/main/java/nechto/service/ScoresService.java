package nechto.service;

import nechto.dto.response.ResponseScoresDto;
import nechto.dto.request.RequestScoresDto;

import java.util.List;

public interface ScoresService {
    List<ResponseScoresDto> countAndSaveAllScoresInTheGame(Long gameId);

    ResponseScoresDto update(RequestScoresDto scores);
}
