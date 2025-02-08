package nechto.dto;

import lombok.Data;
import nechto.enums.Status;

import java.util.List;

@Data
public class ResponseScoresDto {
    private Integer id;
    private UserDto user;
    private ResponseGameDto game;
    private float scores;
    private List<Status> statuses;
}
