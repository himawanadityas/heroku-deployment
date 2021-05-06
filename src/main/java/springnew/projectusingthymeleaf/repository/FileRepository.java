package springnew.projectusingthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springnew.projectusingthymeleaf.model.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {
}
