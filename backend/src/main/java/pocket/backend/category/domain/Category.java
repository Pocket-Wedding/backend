package pocket.backend.category.domain;

import jakarta.persistence.*;
import lombok.*;
import pocket.backend.common.domain.BaseTimeEntity;
import pocket.backend.company.domain.Company;

import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long totalCount = 0L;

    @OneToMany(mappedBy = "category")
    private List<Company> companies;

    @Builder
    public Category(String name){
        this.name = name;
    }

    public void updateCategory(String name){
        Optional.ofNullable(name).ifPresent(n -> this.name = n);
    }

    public void increaseTotalCount(){
        this.totalCount++;
    }

    public void decreaseTotalCount() {
        this.totalCount--;
    }
}
