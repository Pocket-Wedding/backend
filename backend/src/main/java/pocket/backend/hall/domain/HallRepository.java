package pocket.backend.hall.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pocket.backend.hall.domain.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
}
