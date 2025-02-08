package nechto.service;

import lombok.RequiredArgsConstructor;
import nechto.dto.ResponseScoresDto;
import nechto.dto.RequestScoresDto;
import nechto.entity.Game;
import nechto.entity.Scores;
import nechto.entity.User;
import nechto.enums.Status;
import nechto.repository.GameRepository;
import nechto.repository.ScoresRepository;
import nechto.repository.UserRepository;
import nechto.status.StatusInterface;
import nechto.mappers.ScoresMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScoresServiceImpl implements ScoresService {
    private final ScoresRepository scoresRepository;

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    private final StatusFactory statusFactory;

    private final ScoresMapper scoresMapper;

    @Override
    public ResponseScoresDto saveScores(RequestScoresDto scoresDto) {
        Integer userId = scoresDto.getUserId();
        Integer gameId = scoresDto.getGameId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", userId)));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Game with id %s not found", gameId)));
        Scores scores = new Scores(scoresDto.getId(), user, game, scoresDto.getScores(), scoresDto.getStatuses());
        return scoresMapper.convertToScoresDto(scoresRepository.save(scores));
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
