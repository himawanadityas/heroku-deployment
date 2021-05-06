package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = TypeDosen.TABEL_TYPE_DOSEN)
@Data
public class TypeDosen {
    public static final String TABEL_TYPE_DOSEN = "tabel_type_dosen";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABEL_TYPE_DOSEN)
    @SequenceGenerator(name = TABEL_TYPE_DOSEN, sequenceName = "tabel_type_dosen_seq")

    private Integer id;

    private String typeDosen;
}