package nechto.mappers;

import nechto.dto.ScoresDto;
import nechto.entity.Scores;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoresMapper {

    ScoresDto convertToScoresDto(Scores scores);

    Scores convertToScores(ScoresDto scores);

}
