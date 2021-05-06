package springnew.projectusingthymeleaf.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDto {
    private String userName;
    private String password;
    private List<RoleRequestDto> roles;

}
