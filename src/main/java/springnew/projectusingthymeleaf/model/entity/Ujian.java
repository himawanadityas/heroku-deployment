package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = Ujian.TABEL_UJIAN)
@Data
public class Ujian {
    public static final String TABEL_UJIAN = "tabel_ujian";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABEL_UJIAN)
    @SequenceGenerator(name = TABEL_UJIAN, sequenceName = "tabel_ujian_seq")

    private Integer id;

    private String namaUjian;
    private String statusUjian;
}