package pl.tpo4;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    public Book(String name, Publishers bookAuthors, List<Authors> listOfAuthors){
        this.name = name;
        Book_Authors = bookAuthors;
        ListOfAuthors = listOfAuthors;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Publishers Book_Authors;

    @OneToOne
    @JoinColumn(name = "publishers_id")
    Publishers publishers;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Authors> ListOfAuthors;

    public Book() {

    }

    public void addAuthor(Authors a)
    {
        ListOfAuthors.add(a);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPublishers(Publishers publishers) {
        this.publishers = publishers;
    }


    public Publishers getPublishers() {
        return publishers;
    }


    public String getName() {
        return name;
    }

    public Publishers getBook_Authors() {
        return Book_Authors;
    }

    public List<Authors> getListOfAuthors() {
        return ListOfAuthors;
    }
}
