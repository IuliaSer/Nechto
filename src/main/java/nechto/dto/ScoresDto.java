package nechto.dto;

import lombok.Data;
import nechto.entity.Game;
import nechto.entity.User;
import nechto.enums.Status;

import java.util.List;

@Data
public class ScoresDto {
    private Integer id;
    private User user;
    private Game game;
    private float scores;
    private List<Status> statuses;
}
