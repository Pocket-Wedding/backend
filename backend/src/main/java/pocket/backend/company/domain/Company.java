package pocket.backend.company.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pocket.backend.category.domain.Category;
import pocket.backend.common.domain.BaseTimeEntity;
import pocket.backend.company.dto.CompanyRequest;
import pocket.backend.location.domain.Location;

import java.util.Optional;

@Entity
@Getter
@Setter
@EntityListeners({CompanyListener.class})
@NoArgsConstructor
public class Company extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    @Lob
    private String describe;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer price;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Builder
    public Company(String name, String describe, String address, String phoneNumber, Integer price, String imageUrl, Category category, Location location) {
        this.name = name;
        this.describe = describe;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
        this.location = location;
    }
}
