package springnew.projectusingthymeleaf.controller.restapi;

import springnew.projectusingthymeleaf.model.dto.NilaiDto;
import springnew.projectusingthymeleaf.model.entity.Nilai;
import springnew.projectusingthymeleaf.repository.NilaiRepository;
import springnew.projectusingthymeleaf.service.NilaiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/nilai")
public class ApiNilai {
    @Autowired
    private NilaiRepository nilaiRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NilaiService nilaiService;

    @GetMapping()
    public List<NilaiDto> getListNilai() {
        List<Nilai> list = nilaiRepository.findAll();
        List<NilaiDto> nilaiDtos =
                list.stream()
                        .map(b -> mapToDto(b))
                        .collect(Collectors.toList());
        return nilaiDtos;

    }
    @GetMapping("/mhs/{id}")
    public List<Nilai> getNilaiMhs(@PathVariable Integer id) {
         return nilaiRepository.findNilaiByIdMahasiswa(id);


    }

    @GetMapping("/{id}")
    public NilaiDto getNilai(@PathVariable Integer id) {

       Nilai nilai = nilaiRepository.findById(id).get();

        NilaiDto nilaiDto = new NilaiDto();
        modelMapper.map(nilai, nilaiDto); //   modelMapper.map(asal, tujuan);
        modelMapper.map(nilai.getMahasiswa(), nilaiDto);
        modelMapper.map(nilai.getUjian(), nilaiDto);

        nilaiDto.setId(nilai.getId());

        return nilaiDto;

    }

    @PostMapping
    public NilaiDto editSave(@RequestBody NilaiDto nilaiDto) {

        Nilai nilai = modelMapper.map(nilaiDto, Nilai.class);

        nilai.setIdMahasiswa(nilaiDto.getIdMahasiswa());
        nilai.setIdUjian(nilaiDto.getIdUjian());

        nilai = nilaiService.saveNilai(nilai);

        NilaiDto nilaiDto1 = mapToDto(nilai);


        return nilaiDto1;
    }

    private NilaiDto mapToDto(Nilai nilai) {


        NilaiDto nilaiDto = modelMapper.map(nilai, NilaiDto.class);

        nilaiDto.setIdMahasiswa(nilai.getMahasiswa().getId());
        nilaiDto.setIdUjian(nilai.getUjian().getId());
        nilaiDto.setMahasiswa(nilai.getMahasiswa().getNama());
        nilaiDto.setUjian(nilai.getUjian().getNamaUjian());

        nilaiDto.setId(nilai.getId());

        return nilaiDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        nilaiRepository.deleteById(id);
    }

    @DeleteMapping()
    public void delete() {
        nilaiRepository.deleteAll();
    }

}
