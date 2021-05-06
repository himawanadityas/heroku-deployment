package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = Jurusan.TABEL_JURUSAN)
@Data
public class Jurusan {
    public static final String TABEL_JURUSAN = "tabel_jurusan";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABEL_JURUSAN)
    @SequenceGenerator(name = TABEL_JURUSAN, sequenceName = "tabel_jurusan_seq")

    private Integer id;

    private String jurusan;

    private String statusJurusan;

}