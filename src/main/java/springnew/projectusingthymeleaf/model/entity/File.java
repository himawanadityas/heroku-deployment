package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = springnew.projectusingthymeleaf.model.entity.File.FILES)
@Data
public class File {

    public static final String FILES = "tabel_file";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = FILES)
    @SequenceGenerator(name = FILES, sequenceName = "tabel_file_seq", allocationSize = 1)

    private Integer id;
    private String fileName;
    private String contentType;
    private Long size;
    @Lob
    private byte[] file;


}
