package nechto.mappers;

import nechto.dto.ResponseScoresDto;
import nechto.entity.Scores;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoresMapper {

    ResponseScoresDto convertToScoresDto(Scores scores);

    Scores convertToScores(ResponseScoresDto scores);

}
