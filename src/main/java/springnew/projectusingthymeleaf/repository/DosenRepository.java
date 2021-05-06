package springnew.projectusingthymeleaf.repository;

import springnew.projectusingthymeleaf.model.entity.Dosen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface DosenRepository extends JpaRepository<Dosen, Integer>{

}
