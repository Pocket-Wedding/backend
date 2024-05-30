package pocket.backend.company.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    Optional<Company> findByName(String name);
    Optional<List<Company>> findAllByLocationId(Long locationId);
    Optional<List<Company>> findAllByCategoryId(Long categoryId);
    Optional<List<Company>> findAllByLocationIdAndCategoryId(Long locationId, Long categoryId);

}
