package springnew.projectusingthymeleaf.controller.restapi;


import springnew.projectusingthymeleaf.model.dto.BiodataDto;
import springnew.projectusingthymeleaf.model.entity.Biodata;
import springnew.projectusingthymeleaf.model.entity.DetailBiodata;
import springnew.projectusingthymeleaf.repository.BiodataRepository;
import springnew.projectusingthymeleaf.service.BiodataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/biodata")
public class ApiBiodata {
    @Autowired
    private BiodataRepository biodataRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BiodataService biodataService;

    @GetMapping()
    public List<BiodataDto> getListBiodata() {
        // List<Biodata> findAllByAgama(String agama);
        List<Biodata> biodataList = biodataRepository.findAll();
        List<BiodataDto> biodataDtos =
                biodataList.stream()
                        .map(b -> mapBiodataToBiodataDto(b))
                        .collect(Collectors.toList());
        return biodataDtos;
    }

    @GetMapping("/{idAja}")
    public BiodataDto getBiodata(@PathVariable Integer idAja) {
      //  List<Biodata> biodata =
        Biodata biodata = biodataRepository.findById(idAja).get();
        BiodataDto biodataDto = new BiodataDto();

        modelMapper.map(biodata, biodataDto); //             modelMapper.map(asal, tujuan);
        modelMapper.map(biodata.getDetailBiodata(), biodataDto);

        // jika tidak pakai model mapper maka perlu setter getter satu satu

//        biodataDto.setId(biodata.getId());
//        biodataDto.setAlamat(biodata.getAlamat());
//        biodataDto.setNama(biodata.getNama());
//        biodataDto.setTanggalLahir(biodata.getTanggalLahir());

//        biodataDto.setAgama(biodata.getAgama());
//        biodataDto.setEmail(biodata.getEmail());
//        biodataDto.setGender(biodata.getGender());

   //       yang join columns
//        biodataDto.setNamaAyah(biodata.getDetailBiodata().getNamaAyah());
//        biodataDto.setNamaIbu(biodata.getDetailBiodata().getNamaIbu());
//        biodataDto.setPekerjaanAyah(biodata.getDetailBiodata().getPekerjaanAyah());
//        biodataDto.setPekerjaanIbu(biodata.getDetailBiodata().getPekerjaanIbu());
//        biodataDto.setIdDetail(biodata.getDetailBiodata().getId().toString());

       return biodataDto;
    }

    @PostMapping
    public BiodataDto editSaveBiodata(@RequestBody BiodataDto biodataDto) {
        //Mapping data BiodataDto ke DetailBiodata
        DetailBiodata detailBiodata = modelMapper.map(biodataDto, DetailBiodata.class);
        //karena property berbeda antara idDetail pada dto dan id pada detail biodata,
        //maka harus set get manual. tidak bisa pakai model mapper
        //detailBiodata.setId(Integer.parseInt(biodataDto.getIdDetail()));

        //Mapping data BiodataDto ke Biodata
        Biodata biodata = modelMapper.map(biodataDto, Biodata.class);

       // biodata.setId(biodata.getId());
        // set detailbiodata
        biodata.setDetailBiodata(detailBiodata);
        // kumudia save datanya
        biodataService.saveBiodataMaterDetail(biodata);

        /*biodataDtoDb sudah mengandung primary key biodata dan biodatadto*/
        BiodataDto biodataDtoDB = mapBiodataToBiodataDto(biodata);
        /*
         * karena yang di return adalah BiodataDto maka perlu di mapping value dari biodata
         * ke biodataDto
         */

        return biodataDtoDB;
    }

    private BiodataDto mapBiodataToBiodataDto(Biodata biodata) {
        /*
         * melakukan mapping OBJECT biodata kedalam CLASS BARU BIODATADTO (new BiodataDto)
         * makanya destinattionnya adalah BiodataDto.class */



        BiodataDto biodataDto = modelMapper.map(biodata, BiodataDto.class);



        /*perlu juga detailbiodata di mappingkan ke OBJECT biodataDto yang sebelumnya udah berisi biodata
         * detailBiodata perlu juga dimappingkan karena di project ini modelmappernya sudah saya set cuma mapping in satu level*/


        modelMapper.map(biodata.getDetailBiodata(), biodataDto);

        /*karena idDetail dan variable id di detail biodata tidak sama
         * maka harus di mapping manual*/
        biodataDto.setIdDetail(biodata.getDetailBiodata().getId().toString());

        /* ini perlu dilakukan hanya sebagai contoh saja
         * id pada biodataDto seharusnya berisi biodata id, tapi karena dilakukan model mapper terhadap detailBiodata maka
         * id pada biodataDto berisi id detailbiodata
         * seharusnya kalo tidak mau seperti ini urutan mapping nya dibalik.
         * masukkan dulu detailBiodatanya, baru biodatanya*/

        biodataDto.setId(biodata.getId());
        return biodataDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        biodataRepository.deleteById(Integer.parseInt(id));
    }

    @DeleteMapping()
    public void delete() {
        biodataRepository.deleteAll();
    }

    @GetMapping("/transaksi")
    public Biodata latTransactional() {
        Biodata biodata = biodataService.latTransactional();
        return biodata;
    }
}
