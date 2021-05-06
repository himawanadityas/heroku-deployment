package springnew.projectusingthymeleaf.controller.restapi;

import org.modelmapper.ModelMapper;
import springnew.projectusingthymeleaf.model.entity.Jurusan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springnew.projectusingthymeleaf.repository.JurusanRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jurusan")
public class ApiJurusan {
    @Autowired
    private JurusanRepository jurusanRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Jurusan> getAll() {
        return jurusanRepository.findAll();

    }

    @GetMapping("/{id}")
    public Jurusan getJurusan(@PathVariable Integer id) {

        return jurusanRepository.findById(id).get();


    }
    @PostMapping
    public Jurusan save(@RequestBody Jurusan jurusan) {

        return jurusanRepository.save(jurusan);
    }

    @DeleteMapping("/{id}")
    public void deleteRowJurusan(@PathVariable Integer id) {
        jurusanRepository.deleteById(id);
    }

}

