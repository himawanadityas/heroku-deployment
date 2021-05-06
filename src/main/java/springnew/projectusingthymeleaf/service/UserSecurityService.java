package springnew.projectusingthymeleaf.service;

import springnew.projectusingthymeleaf.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService extends UserDetailsService {
    User save(User user);
}
