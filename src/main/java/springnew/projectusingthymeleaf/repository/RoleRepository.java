package springnew.projectusingthymeleaf.repository;

import springnew.projectusingthymeleaf.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
