package springnew.projectusingthymeleaf.model.dto;

import lombok.Data;

@Data
public class BiodataDto {
    private Integer id;
    private String nama;
    private String alamat;
    private String email;
    private String agama;
    private String gender;
    private String namaAyah;
    private String namaIbu;
    private String pekerjaanAyah;
    private String pekerjaanIbu;
 //   private String alamatOrangTua;
    private String tanggalLahir;
    private String idDetail;
}
