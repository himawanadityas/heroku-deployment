package springnew.projectusingthymeleaf.controller.restapi;

import org.modelmapper.ModelMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import springnew.projectusingthymeleaf.model.dto.FileDto;
import springnew.projectusingthymeleaf.model.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springnew.projectusingthymeleaf.repository.FileRepository;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileApi {


    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public File save(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        File file = new File();
        file.setFileName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        file.setContentType(multipartFile.getContentType());
        file.setFile(multipartFile.getBytes());
        file.setSize(multipartFile.getSize());
//        file.setFile(multipartFile.getBytes());
        fileRepository.save(file);
        return file;
    }

    @GetMapping("/{id}")
    public FileDto getFile(@PathVariable Integer id) {
        File file = fileRepository.findById(id).get();
        FileDto fileDto = new FileDto();
//        fileDto.setFileName(file.getFileName());
        fileDto.setFile(file.getFile());
        fileDto.setContentType(file.getContentType());
        return fileDto;
    }
}
