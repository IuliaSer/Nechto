package nechto.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RequestGameDto {
    private Integer id;

    private Date date;

    private List<Integer> userIds;
}
