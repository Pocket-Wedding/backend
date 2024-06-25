package pocket.backend.hall.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    boolean existsByName(String name);
    Optional<Hall> findByName(String name);
}
