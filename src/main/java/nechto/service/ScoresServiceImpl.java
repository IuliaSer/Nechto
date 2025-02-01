package nechto.service;

import lombok.RequiredArgsConstructor;
import nechto.dto.ScoresDto;
import nechto.entity.Scores;
import nechto.enums.Status;
import nechto.repository.ScoresRepository;
import nechto.status.StatusInterface;
import nechto.mappers.ScoresMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScoresServiceImpl implements ScoresService {
    private final ScoresRepository scoresRepository;

    private final StatusFactory statusFactory;

    private final ScoresMapper scoresMapper;

    @Override
    public ScoresDto saveScores(ScoresDto scores) {
        return scoresMapper.convertToScoresDto(scoresRepository.save(scoresMapper.convertToScores(scores)));
    }
    
    @Override
    public void countForAll(Integer gameId) {
        List<Scores> scoresList = scoresRepository.findAllByGameId(gameId);
        for (Scores scores : scoresList) {
            List<Status> statuses = scores.getStatuses();
            float results = 0;

            List<StatusInterface> statusInterfaces = new ArrayList<>();
            for (Status status : statuses) {
                StatusInterface statusInterface = statusFactory.createStatus(status);
                statusInterfaces.add(statusInterface);
            }
            for (StatusInterface status : statusInterfaces) {
                results += status.count(statuses, scoresList);
            }
            scores.setScores(results);
            scoresRepository.save(scores);
        }
    }
}
