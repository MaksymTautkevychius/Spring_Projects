package pl.tpo4;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
public class Publishers {

    public Publishers(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Name;

    @Column
    private String Surname;

    @OneToOne(mappedBy = "publishers", cascade = CascadeType.ALL)
    Book book;

    @OneToMany(mappedBy = "Book_Authors", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Book> BookList = new ArrayList<>();



    public List<Book> getBookList() {
        return BookList;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
