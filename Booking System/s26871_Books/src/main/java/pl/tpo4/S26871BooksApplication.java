package pl.tpo4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class S26871BooksApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(S26871BooksApplication.class,args);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        AuthorsRepository authorsRepository = context.getBean(AuthorsRepository.class);
        PublishersRepository publishersRepository = context.getBean(PublishersRepository.class);
        //bookRepository.listAllBooks();
        authorsRepository.listAllAuthors();
        for (Authors a: authorsRepository.listAllAuthors())
        {
            System.out.println(a);
        }
    }

}
