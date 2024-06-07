package pocket.backend.company.domain;

import jakarta.persistence.*;
import pocket.backend.category.domain.Category;
import pocket.backend.location.domain.Location;

public class CompanyListener {
    @PostPersist
    @PostRemove
    public void updateTotalCount(Company company) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            Location location = company.getLocation();
            location.setTotalCount((long) location.getCompanies().size());

            Category category = company.getCategory();
            category.setTotalCount((long) category.getCompanies().size());

            em.merge(location);
            em.merge(category);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}
