package springnew.projectusingthymeleaf.controller.restapi;

import springnew.projectusingthymeleaf.model.entity.TypeDosen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springnew.projectusingthymeleaf.repository.TypeDosenRepository;

import java.util.List;

@RestController
@RequestMapping("/api/typedosen")
public class ApiTypeDosen {
    @Autowired
    private TypeDosenRepository typeDosenRepository;


    @GetMapping
    public List<TypeDosen> getAll() {
        return typeDosenRepository.findAll();

    }
}

