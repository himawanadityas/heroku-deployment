package springnew.projectusingthymeleaf.controller.restapi;

import springnew.projectusingthymeleaf.model.dto.DosenDto;
import springnew.projectusingthymeleaf.model.entity.Dosen;
import springnew.projectusingthymeleaf.repository.DosenRepository;
import springnew.projectusingthymeleaf.service.DosenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dosen")
public class ApiDosen {
    @Autowired
    private DosenRepository dosenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DosenService dosenService;

    @GetMapping()
    public List<DosenDto> getListBiodata() {
        List<Dosen> dosenList = dosenRepository.findAll();
        List<DosenDto> dosenDtos =
                dosenList.stream()
                        .map(b -> mapToDto(b))
                        .collect(Collectors.toList());
        return dosenDtos;

    }

    @GetMapping("/{id}")
    public DosenDto getDosen(@PathVariable Integer id) {

        Dosen dosen = dosenRepository.findById(id).get();

        DosenDto dosenDto = new DosenDto();

        modelMapper.map(dosen, dosenDto); //   modelMapper.map(asal, tujuan);
        modelMapper.map(dosen.getTypeDosen(), dosenDto);
        modelMapper.map(dosen.getJurusan(), dosenDto);

        dosenDto.setId(dosen.getId());

        return dosenDto;

    }

    @PostMapping
    public DosenDto editSave(@RequestBody DosenDto dosenDto) {

        Dosen dosen = modelMapper.map(dosenDto, Dosen.class);

        dosen.setIdJurusan(dosenDto.getIdJurusan());
        dosen.setIdTypeDosen(dosenDto.getIdTypeDosen());

        dosen = dosenService.saveDosen(dosen);

        DosenDto dosenDto1 = mapToDto(dosen);


        return dosenDto1;
    }

    private DosenDto mapToDto(Dosen dosen) {

        DosenDto dosenDto = modelMapper.map(dosen, DosenDto.class);

        dosenDto.setIdTypeDosen(dosen.getTypeDosen().getId());
        dosenDto.setIdJurusan(dosen.getJurusan().getId());
        dosenDto.setTypeDosen(dosen.getTypeDosen().getTypeDosen());
        dosenDto.setJurusan(dosen.getJurusan().getJurusan());

        dosenDto.setId(dosen.getId());


        return dosenDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        dosenRepository.deleteById(id);
    }

    @DeleteMapping()
    public void delete() {
        dosenRepository.deleteAll();
    }

//    @GetMapping("/transaksi")
//    public Biodata latTransactional() {
//        Biodata biodata = mahasiswaService.latTransactional();
//        return biodata;
//    }
}
