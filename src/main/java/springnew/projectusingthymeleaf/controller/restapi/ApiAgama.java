package springnew.projectusingthymeleaf.controller.restapi;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import springnew.projectusingthymeleaf.model.entity.Agama;
import springnew.projectusingthymeleaf.model.dto.AgamaDto;
import springnew.projectusingthymeleaf.repository.AgamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springnew.projectusingthymeleaf.service.AgamaService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agama")
public class ApiAgama {
    @Autowired
    private AgamaRepository agamaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AgamaService agamaService;

    @GetMapping
    public List<Agama> getAll() {
        return agamaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Agama getAgama(@PathVariable Integer id) {
        return agamaRepository.findById(id).get();
    }

    @PostMapping
    public Agama save(@RequestBody Agama agama) {
        return agamaRepository.save(agama);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AgamaDto editSave(@RequestPart(value = "agama", required = true) AgamaDto agamaDto,
                                 @RequestPart(value = "file", required = true) MultipartFile file) throws Exception {

        String userFolderPath = "C://download";
        Path path = Paths.get(userFolderPath);
        Path filePath = path.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Upload file with size" + file.getSize() + " with name :  " + file.getOriginalFilename());

        Agama agama = modelMapper.map(agamaDto, Agama.class);
        agama.setId(agamaDto.getId());
//        String pat = filePath.toUri().getPath();
        agama.setFile(file.getOriginalFilename());
        agama = agamaService.saveAgama(agama);
        AgamaDto agamaDto1 = mapToDto(agama);
        return agamaDto1;
    }

    @DeleteMapping("/{id}")
    public void deleteRowAgama(@PathVariable Integer id) {
        agamaRepository.deleteById(id);
    }

        private AgamaDto mapToDto (Agama agama){
            AgamaDto agamaDto = modelMapper.map(agama, AgamaDto.class);
            agamaDto.setId(agama.getId());
            return agamaDto;
        }
    }