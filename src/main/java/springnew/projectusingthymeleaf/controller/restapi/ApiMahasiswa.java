package springnew.projectusingthymeleaf.controller.restapi;

import springnew.projectusingthymeleaf.model.dto.MahasiswaDto;
import springnew.projectusingthymeleaf.model.entity.Mahasiswa;
import springnew.projectusingthymeleaf.model.entity.Agama;
import springnew.projectusingthymeleaf.model.entity.Jurusan;
import springnew.projectusingthymeleaf.repository.MahasiswaRepository;
import springnew.projectusingthymeleaf.service.MahasiswaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mahasiswa")
public class ApiMahasiswa {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MahasiswaService mahasiswaService;

    @GetMapping()
    public List<MahasiswaDto> getListMahasiswa() {
        List<Mahasiswa> biodataList = mahasiswaRepository.findAll();
        List<MahasiswaDto> mahasiswaDto =
                biodataList.stream()
                        .map(b -> mapMahasiswaToMahasiswaDto(b))
                        .collect(Collectors.toList());
        return mahasiswaDto;
    }

    @GetMapping("/maha")
    public MahasiswaDto getAllMaha() {
        MahasiswaDto mahasiswaDto = new MahasiswaDto();
        mahasiswaDto.setId(1);
        mahasiswaDto.setJurusan("TI");
        mahasiswaDto.setAgama("Islam");
        return mahasiswaDto;

    }

    @PostMapping("/mahas")
    public Mahasiswa postMaha(@RequestBody MahasiswaDto mahasiswaDto) {
        Mahasiswa mahasiswa = modelMapper.map(mahasiswaDto, Mahasiswa.class);

        return mahasiswa;
    }

    @GetMapping("/{id}")
    public MahasiswaDto getMahasiswa(@PathVariable Integer id) {

        Mahasiswa mahasiswa = mahasiswaRepository.findById(id).get();

        MahasiswaDto mahasiswaDto = new MahasiswaDto();

        modelMapper.map(mahasiswa, mahasiswaDto); //   modelMapper.map(asal, tujuan);
        modelMapper.map(mahasiswa.getAgama(), mahasiswaDto);
        modelMapper.map(mahasiswa.getJurusan(), mahasiswaDto);

        mahasiswaDto.setId(mahasiswa.getId());
        return mahasiswaDto;

    }

    @PostMapping
    public MahasiswaDto editSaveMahasiswa(@RequestBody MahasiswaDto mahasiswaDto) {

        Mahasiswa mahasiswa = modelMapper.map(mahasiswaDto, Mahasiswa.class);

        mahasiswa.setIdAgama(mahasiswaDto.getIdAgama());
        mahasiswa.setIdJurusan(mahasiswaDto.getIdJurusan());

        //mahasiswa = mahasiswaService.saveService(mahasiswa); //tambahan

        MahasiswaDto mahasiswaDtoDB = mapMahasiswaToMahasiswaDto(mahasiswaService.saveService(mahasiswa));

        return mahasiswaDtoDB;
    }

    private MahasiswaDto mapMahasiswaToMahasiswaDto(Mahasiswa mahasiswa) {

        MahasiswaDto mahasiswaDto = modelMapper.map(mahasiswa, MahasiswaDto.class);

        mahasiswaDto.setIdAgama(mahasiswa.getAgama().getId());
        mahasiswaDto.setIdJurusan(mahasiswa.getJurusan().getId());
        mahasiswaDto.setAgama(mahasiswa.getAgama().getAgama());
        mahasiswaDto.setJurusan(mahasiswa.getJurusan().getJurusan());

        mahasiswaDto.setId(mahasiswa.getId());

        return mahasiswaDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        mahasiswaRepository.deleteById(id);
        // mahasiswaRepository.deleteById(Integer.parseInt(id));
    }

    @DeleteMapping()
    public void deleteAllData() {
        mahasiswaRepository.deleteAll();
    }

//    @GetMapping("/transaksi")
//    public Biodata latTransactional() {
//        Biodata biodata = mahasiswaService.latTransactional();
//        return biodata;
//    }
}
