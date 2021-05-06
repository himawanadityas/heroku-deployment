package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Biodata.TABLE_NAME)
@Data
public class Biodata {
    public static final String TABLE_NAME = "t_biodata1";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name=TABLE_NAME , sequenceName = "t_biodata1_seq")
   // @Column(name = "id_bio")
    private Integer id;
   // @Column(name = "nama", nullable = false)
    private String nama;
   // @Column(name = "alamat", nullable = false)
    private String alamat;
    //@Column(name = "e-mail", nullable = false)
    private String email;
   // @Column(name = "agama", nullable = false)
    private String agama;
   // @Column(name = "jenis_kelamin", nullable = false)
    private String gender;
  //  @Column(name = "tanggal_lahir", nullable = false)
    private String tanggalLahir;

    @OneToOne
    @JoinColumn(name = "biodata_id", nullable = false)
    private DetailBiodata detailBiodata;


}
