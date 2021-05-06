package springnew.projectusingthymeleaf.service;

import springnew.projectusingthymeleaf.model.entity.Mahasiswa;
import springnew.projectusingthymeleaf.repository.MahasiswaRepository;
import springnew.projectusingthymeleaf.repository.JurusanRepository;
import springnew.projectusingthymeleaf.repository.AgamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class MahasiswaServiceImpl implements MahasiswaService {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    @Autowired
    private JurusanRepository jurusanRepository;
    @Autowired
    private AgamaRepository agamaRepository;

//    @Override
//    public Biodata latTransactional() {
//        Biodata biodata = biodataRepository.findById(6).get();
//        biodata.setNama("perubahan 1");
//        biodataRepository.save(biodata);
//
//        Integer.parseInt("errorword");
//
//        biodata.setNama("perubahan 2");
//
//        biodataRepository.save(biodata);
//
//        return biodata;
//    }

    @Override
    public Mahasiswa saveService(Mahasiswa mahasiswa) {

        mahasiswa = mahasiswaRepository.save(mahasiswa);
        mahasiswa.setJurusan(jurusanRepository.findById(mahasiswa.getIdJurusan()).get()); // mendapatkan nama jurusan
        mahasiswa.setAgama(agamaRepository.findById(mahasiswa.getIdAgama()).get());

        return mahasiswa;
    }


}
