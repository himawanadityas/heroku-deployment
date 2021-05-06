package springnew.projectusingthymeleaf.repository;

import springnew.projectusingthymeleaf.model.entity.DetailBiodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<DetailBiodata, Integer> {

   // List<DetailBiodata> findAllByGolonganDarah(String golonganDarah);

}
