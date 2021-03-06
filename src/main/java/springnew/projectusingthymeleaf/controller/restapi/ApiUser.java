package springnew.projectusingthymeleaf.controller.restapi;


import springnew.projectusingthymeleaf.model.dto.RoleResponseDto;
import springnew.projectusingthymeleaf.model.dto.UserRequestDto;
import springnew.projectusingthymeleaf.model.dto.UserResponseDto;
import springnew.projectusingthymeleaf.model.entity.Role;
import springnew.projectusingthymeleaf.model.entity.User;
import springnew.projectusingthymeleaf.repository.UserRepository;
import springnew.projectusingthymeleaf.service.UserSecurityService;
import springnew.projectusingthymeleaf.util.UserAuth;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class ApiUser {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSecurityService userSecurityService;

    @GetMapping()
    public List<UserResponseDto> getList() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos =
                users.stream()
                        .map(user -> mapUserToUserResponseDto(user))
                        .collect(Collectors.toList());
        return userResponseDtos;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable("id") String id) {
        User user = userRepository.findById(id).get();

        return mapUserToUserResponseDto(user);
    }

    @PostMapping
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto) {
        User userLogin = UserAuth.getUser();
        Date currentDate = new Date();

        List<Role> roles = userRequestDto.getRoles().stream()
                .map(role -> modelMapper.map(role, Role.class))
                .collect(Collectors.toList());

        User user = modelMapper.map(userRequestDto, User.class);
        user.setRoles(roles);
        user.setCreatedBy(userLogin.getId());
        user.setCreatedOn(currentDate);

        User userResponse = userSecurityService.save(user);

        UserResponseDto userResponseDto = mapUserToUserResponseDto(userResponse);

        return userResponseDto;
    }

    private UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        List<RoleResponseDto> roleResponseDtos = user.getRoles().stream()
                .map(role -> modelMapper.map(role, RoleResponseDto.class))
                .collect(Collectors.toList());
        userResponseDto.setRoles(roleResponseDtos);
        return userResponseDto;
    }

}
