package springnew.projectusingthymeleaf.model.dto;
import lombok.Data;

@Data
public class NilaiDto {
    private Integer id;
    private Integer nilai;
    private String mahasiswa;
    private String ujian;

    private Integer idMahasiswa;
    private Integer idUjian;

}
