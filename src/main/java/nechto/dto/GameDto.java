package nechto.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GameDto {
    private Integer id;

    private Date date;

    private List<UserDto> users;
}
