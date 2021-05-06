package springnew.projectusingthymeleaf.model.entity;


import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = Dosen.TABEL_DOSEN)
@Data
public class Dosen {
    public static final String TABEL_DOSEN= "tabel_dosen";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABEL_DOSEN)
    @SequenceGenerator(name = TABEL_DOSEN, sequenceName = "tabel_dosen_seq")

    private Integer id;
    private String namaDosen;

    @ManyToOne
    @JoinColumn(name="id_jurusan", insertable=false, updatable = false)
    private Jurusan jurusan;

    @Column(name="id_jurusan", nullable = false)
    private Integer idJurusan;


    @ManyToOne
    @JoinColumn(name="id_type_dosen", insertable = false, updatable = false)
    private TypeDosen typeDosen;

    @Column(name="id_type_dosen", nullable = false)
    private Integer idTypeDosen;
}