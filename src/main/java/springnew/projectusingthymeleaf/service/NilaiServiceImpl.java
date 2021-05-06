package springnew.projectusingthymeleaf.service;

import springnew.projectusingthymeleaf.model.entity.Nilai;
import springnew.projectusingthymeleaf.repository.NilaiRepository;
import springnew.projectusingthymeleaf.repository.MahasiswaRepository;
import springnew.projectusingthymeleaf.repository.UjianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class NilaiServiceImpl implements NilaiService {
    @Autowired
    private NilaiRepository nilaiRepository;
    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    @Autowired
    private UjianRepository ujianRepository;

    @Override
    public Nilai saveNilai(Nilai nilai) {


        nilai = nilaiRepository.save(nilai);
        nilai.setMahasiswa(mahasiswaRepository.findById(nilai.getIdMahasiswa()).get());
        nilai.setUjian(ujianRepository.findById(nilai.getIdUjian()).get());
        return nilai;
    }
}