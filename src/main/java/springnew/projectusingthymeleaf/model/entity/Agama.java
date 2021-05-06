package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = Agama.TABEL_AGAMA)
@Data
public class Agama {
    public static final String TABEL_AGAMA = "tabel_agama";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABEL_AGAMA)
    @SequenceGenerator(name = TABEL_AGAMA, sequenceName = "tabel_agama_seq")

    private Integer id;

    private String agama;

    private String file;

}