package nechto.dto;

import lombok.Data;
import nechto.enums.Role;

@Data
public class UserDto {
    private Integer id;

    private String name;

    private String username;

    private String password;

    private Role role;
}

