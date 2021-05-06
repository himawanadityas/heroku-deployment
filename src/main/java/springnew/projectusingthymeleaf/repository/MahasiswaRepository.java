package springnew.projectusingthymeleaf.repository;

import springnew.projectusingthymeleaf.model.entity.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Integer> {

    Mahasiswa findByAlamat(String alamat);
}
