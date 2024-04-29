package pl.tpo4;

import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorsRepository  extends CrudRepository<Authors, Id> {

    @Transactional
    @Query("SELECT b FROM Authors b")
    List<Authors> listAllAuthors();
}
