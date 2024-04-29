package pl.tpo4;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "ListOfAuthors")
    List<Book> books = new ArrayList<>();

    @Column
    String Name;
    @Column
    String Surname;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
