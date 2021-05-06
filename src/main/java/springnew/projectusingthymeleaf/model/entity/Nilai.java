package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = Nilai.TABEL_NILAI)
@Data
public class Nilai {
    public static final String TABEL_NILAI= "tabel_nilai";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABEL_NILAI)
    @SequenceGenerator(name = TABEL_NILAI, sequenceName = "tabel_nilai_seq")

    private Integer id;
    private Integer nilai;

    @ManyToOne
    @JoinColumn(name="id_mahasiswa", insertable = false, updatable = false)
    private Mahasiswa mahasiswa;

    @Column(name="id_mahasiswa", nullable = false)
    private Integer idMahasiswa;

    @ManyToOne
    @JoinColumn(name="id_ujian", insertable = false, updatable = false)
    private Ujian ujian;

    @Column(name="id_ujian", nullable = false)
    private Integer idUjian;
}