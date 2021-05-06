package springnew.projectusingthymeleaf.controller.restapi;


import springnew.projectusingthymeleaf.model.dto.RoleRequestDto;
import springnew.projectusingthymeleaf.model.dto.RoleResponseDto;
import springnew.projectusingthymeleaf.model.entity.Role;
import springnew.projectusingthymeleaf.model.entity.User;
import springnew.projectusingthymeleaf.repository.RoleRepository;
import springnew.projectusingthymeleaf.util.UserAuth;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/role")
public class ApiRole {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public List<RoleResponseDto> getList() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDto> roleResponseDtos =
                roles.stream()
                        .map(role -> modelMapper.map(role, RoleResponseDto.class))
                        .collect(Collectors.toList());
        return roleResponseDtos;
    }

    @PostMapping
    public RoleResponseDto save(@RequestBody RoleRequestDto roleRequestDto) {
        User userLogin = UserAuth.getUser();
        Role role = modelMapper.map(roleRequestDto, Role.class);

        role.setCreatedBy(userLogin.getId());
        role.setCreatedOn(new Date());
        Role roleResponse = roleRepository.save(role);
        RoleResponseDto roleResponseDto = modelMapper.map(roleResponse, RoleResponseDto.class);

        return roleResponseDto;
    }


}
