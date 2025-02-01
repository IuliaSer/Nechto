package nechto.repository;

import nechto.entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoresRepository extends JpaRepository<Scores, Integer> {

//    @Query(value = "SELECT * FROM scores s INNER JOIN game g ON g.id = s.game_id WHERE g.id = :gameId")
    List<Scores> findAllByGameId(Integer gameId);
}
