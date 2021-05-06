/**
 *
 */
package springnew.projectusingthymeleaf.util;

import springnew.projectusingthymeleaf.model.entity.User;
import springnew.projectusingthymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class UserAuth {
    @Autowired
    private static UserRepository userRepository;

    public static User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByUserName(userDetail.getUsername());

        return user;
    }


}
