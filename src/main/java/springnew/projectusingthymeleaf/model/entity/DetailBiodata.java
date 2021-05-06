package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Data
@Table(name = DetailBiodata.TABLENAME)
public class DetailBiodata {
    public static final String TABLENAME = "t_detail1";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLENAME)
    @SequenceGenerator(name=TABLENAME , sequenceName = "t_detail1_seq")
    private Integer id;

    private String namaAyah;

    private String namaIbu;

    private String pekerjaanAyah;

    private String pekerjaanIbu;

   // private String alamatOrangTua;


}
