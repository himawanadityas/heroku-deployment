package springnew.projectusingthymeleaf.controller.restapi;

import springnew.projectusingthymeleaf.model.entity.Ujian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springnew.projectusingthymeleaf.repository.UjianRepository;

import java.util.List;

@RestController
@RequestMapping("/api/ujian")
public class ApiUjian {
    @Autowired
    private UjianRepository ujianRepository;


    @GetMapping
    public List<Ujian> getAll() {
        return ujianRepository.findAll();

    }
}

