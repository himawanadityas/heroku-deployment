package springnew.projectusingthymeleaf.model.dto;
import lombok.Data;

@Data
public class FileDto {
    private Integer id;
    private String fileName;
    private String contentType;
    private Long size;
    private byte[] file;
}
