package springnew.projectusingthymeleaf.model.dto;
import lombok.Data;

@Data

public class MahasiswaDto {
    private Integer id;
    private String nama;
    private String alamat;
    private String jurusan;
    private String agama;
    private String statusJurusan;


    private Integer idJurusan;
    private Integer idAgama;

}
