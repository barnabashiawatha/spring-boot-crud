package crud.repository;

import crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
