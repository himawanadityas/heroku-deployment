package springnew.projectusingthymeleaf.service;

import springnew.projectusingthymeleaf.model.entity.Role;
import springnew.projectusingthymeleaf.model.entity.User;
import springnew.projectusingthymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserSecurityServiceImpl implements springnew.projectusingthymeleaf.service.UserSecurityService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), user.getEnabled(), true, true,
                true, getAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public User save(User user) {
        user.setEnabled(Boolean.TRUE);
        user.setTokenExpired(Boolean.FALSE);
        user.setIsDelete(Boolean.FALSE);
        if (user.getId() == null || user.getId().equalsIgnoreCase("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }


}
