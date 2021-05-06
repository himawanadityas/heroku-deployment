package springnew.projectusingthymeleaf.service;

import springnew.projectusingthymeleaf.model.entity.Biodata;
import springnew.projectusingthymeleaf.repository.BiodataRepository;
import springnew.projectusingthymeleaf.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BiodataServiceImpl implements BiodataService {
    @Autowired
    private BiodataRepository biodataRepository;
    @Autowired
    private DetailRepository detailRepository;

    @Override
    public Biodata latTransactional() {
        Biodata biodata = biodataRepository.findById(6).get();
        biodata.setNama("perubahan 1");
        biodataRepository.save(biodata);

        Integer.parseInt("errorword");

        biodata.setNama("perubahan 2");

        biodataRepository.save(biodata);

        return biodata;
    }

    @Override
    public Biodata saveBiodataMaterDetail(Biodata biodata) {
        /*
        coba di debug disini
        1. save detailBiodata terlebih dahulu
        * */
        detailRepository.save(biodata.getDetailBiodata());

        /*
         * jika di dibug setelah save detailBiodata maka id / primary key yang tadi nya null akan mempunyai nilai
         * dari generate sequance
         *
         * sehingga kita bisa save biodata karena primary key biodataDetail sudah ada
         * */
        biodataRepository.save(biodata);


        return biodata;
    }
}
