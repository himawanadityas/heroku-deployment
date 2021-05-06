package springnew.projectusingthymeleaf.service;

import springnew.projectusingthymeleaf.model.entity.Dosen;
import springnew.projectusingthymeleaf.repository.DosenRepository;
import springnew.projectusingthymeleaf.repository.TypeDosenRepository;
import springnew.projectusingthymeleaf.repository.JurusanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class DosenServiceImpl implements DosenService {
    @Autowired
    private DosenRepository dosenRepository;
    @Autowired
    private JurusanRepository jurusanRepository;
    @Autowired
    private TypeDosenRepository typeDosenRepository;

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
    public Dosen saveDosen(Dosen dosen) {

        dosen = dosenRepository.save(dosen);
        dosen.setJurusan(jurusanRepository.findById(dosen.getIdJurusan()).get()); // mendapatkan nama jurusan
        dosen.setTypeDosen(typeDosenRepository.findById(dosen.getIdTypeDosen()).get());

        return dosen;
    }
}
