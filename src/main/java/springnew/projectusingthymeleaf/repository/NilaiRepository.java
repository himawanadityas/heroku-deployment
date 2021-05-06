package springnew.projectusingthymeleaf.repository;

import org.springframework.data.jpa.repository.Query;
import springnew.projectusingthymeleaf.model.entity.Nilai;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NilaiRepository extends JpaRepository<Nilai, Integer>{
    @Query(value = "SELECT n FROM Nilai n WHERE n.idMahasiswa = ?1", nativeQuery = false)
    List<Nilai> findNilaiByIdMahasiswa(Integer id);

}
