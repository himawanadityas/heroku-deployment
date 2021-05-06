package springnew.projectusingthymeleaf.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_book")
@Data
public class Book {

    @Id
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_biodata")
    private Biodata pengarang;
}
