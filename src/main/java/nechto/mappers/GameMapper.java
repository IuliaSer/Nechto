package nechto.mappers;

import nechto.dto.ResponseGameDto;
import nechto.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    ResponseGameDto convertToGameDto(Game game);

    Game convertToGame(ResponseGameDto Game);

}
