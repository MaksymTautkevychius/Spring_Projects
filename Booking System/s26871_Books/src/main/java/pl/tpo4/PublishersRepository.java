package pl.tpo4;

import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishersRepository extends CrudRepository<Publishers, Id> {
}
