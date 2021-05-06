package springnew.projectusingthymeleaf.controller.restapi;

import springnew.projectusingthymeleaf.model.entity.DetailBiodata;
import springnew.projectusingthymeleaf.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail")
public class ApiDetail {
    @Autowired
    private DetailRepository detailRepository;

    @GetMapping
    public List<DetailBiodata> getAll() {

        return detailRepository.findAll();
    }

    @PostMapping
    public DetailBiodata save(@RequestBody DetailBiodata detailBiodata) {

        return detailRepository.save(detailBiodata);
    }

//    @GetMapping("/{golonganDarah}")
//    public List<DetailBiodata> getByGolonganDarah(@PathVariable String golonganDarah) {
//        return detailRepository.findAllByGolonganDarah(golonganDarah);
//    }
}
