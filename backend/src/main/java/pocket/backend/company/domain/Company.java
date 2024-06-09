package pocket.backend.company.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pocket.backend.category.domain.Category;
import pocket.backend.common.domain.BaseTimeEntity;
import pocket.backend.company.dto.CompanyUpdateRequestDTO;
import pocket.backend.location.domain.Location;

import java.util.Optional;

@Entity
@Getter
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
    @JsonManagedReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @JsonManagedReference
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

    public void update(CompanyUpdateRequestDTO companyUpdateRequestDTO){
        Optional.ofNullable(companyUpdateRequestDTO.getName()).ifPresent(name -> this.name = name);
        Optional.ofNullable(companyUpdateRequestDTO.getDescribe()).ifPresent(describe -> this.describe = describe);
        Optional.ofNullable(companyUpdateRequestDTO.getAddress()).ifPresent(address -> this.address = address);
        Optional.ofNullable(companyUpdateRequestDTO.getPhoneNumber()).ifPresent(phoneNumber -> this.phoneNumber = phoneNumber);
        Optional.ofNullable(companyUpdateRequestDTO.getPrice()).ifPresent(price -> this.price = price);
        Optional.ofNullable(companyUpdateRequestDTO.getImageUrl()).ifPresent(imageUrl -> this.imageUrl = imageUrl);
    }
}
