package pocket.backend.location.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    Boolean existsByName(String locationName);
    Optional<Location> findByName(String locationName);
}
