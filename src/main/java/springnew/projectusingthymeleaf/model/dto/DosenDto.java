package springnew.projectusingthymeleaf.model.dto;
import lombok.Data;

@Data

public class DosenDto {
    private Integer id;
    private String namaDosen;
    private String typeDosen;
    private String jurusan;

    private Integer idJurusan;
    private Integer idTypeDosen;
}
