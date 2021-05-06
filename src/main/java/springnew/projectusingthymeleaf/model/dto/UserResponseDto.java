package springnew.projectusingthymeleaf.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private String userName;
    private String password;
    private List<RoleResponseDto> roles;

}
