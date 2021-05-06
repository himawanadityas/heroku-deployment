package springnew.projectusingthymeleaf.repository;
import springnew.projectusingthymeleaf.model.entity.Jurusan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface JurusanRepository extends JpaRepository<Jurusan, Integer> {

    Jurusan findByJurusan(String jurusan);

}
