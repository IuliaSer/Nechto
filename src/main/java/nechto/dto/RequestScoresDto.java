package nechto.dto;

import lombok.Data;
import nechto.enums.Status;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RequestScoresDto {
    private Integer id;

    @NotNull(message = "User id should not be null")
    private Integer userId;

    @NotNull(message = "Game id should not be null")
    private Integer gameId;

    @NotNull(message = "Scores should not be null")
    private float scores;

    @NotNull(message = "Statuses should not be null")
    private List<Status> statuses;
}
