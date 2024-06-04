package pocket.backend.hall.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pocket.backend.hall.domain.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {

}