package crud.repository;

import crud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
    List<Customer> findAll();
    long deleteByName(String name);
}