package nechto.mappers;

import nechto.dto.GameDto;
import nechto.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDto convertToGameDto(Game game);

    Game convertToGame(GameDto Game);

}
