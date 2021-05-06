package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = Mahasiswa.TABEL_MAHASISWA)
@Data
public class Mahasiswa {
    public static final String TABEL_MAHASISWA = "tabel_mahasiswa";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABEL_MAHASISWA)
    @SequenceGenerator(name=TABEL_MAHASISWA , sequenceName = "tabel_mahasiswa_seq")

    private Integer id;
    private String nama;
    private String alamat;

    @ManyToOne
    @JoinColumn(name="id_jurusan", updatable = false, insertable = false)
    private Jurusan jurusan;

    @Column(name="id_jurusan", nullable = false)
    private Integer idJurusan;

    @ManyToOne
    @JoinColumn(name="id_agama", updatable = false, insertable = false)
    private Agama agama;

    @Column(name="id_agama", nullable = false)
    private Integer idAgama;
}
