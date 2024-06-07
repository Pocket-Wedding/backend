package pocket.backend.location.domain;

import jakarta.persistence.*;
import lombok.*;
import pocket.backend.common.domain.BaseTimeEntity;
import pocket.backend.company.domain.Company;
import pocket.backend.location.dto.LocationRequest;

import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Location extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Setter
    private Long totalCount = 0L;

    @OneToMany(mappedBy="location")
    public List<Company> companies;

    @Builder
    public Location(String name){
        this.name = name;
    }

    public void updateLocation(LocationRequest locationRequest){
        Optional.ofNullable(locationRequest.getName()).ifPresent(name -> this.name = name);
    }
}
