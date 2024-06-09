package pocket.backend.location.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pocket.backend.common.domain.BaseTimeEntity;
import pocket.backend.company.domain.Company;
import pocket.backend.location.dto.LocationUpdateRequest;

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
    private Long totalCount = 0L;

    @OneToMany(mappedBy="location")
    public List<Company> companies;

    @Builder
    public Location(String name){
        this.name = name;
    }

    public void updateLocation(LocationUpdateRequest locationUpdateRequest){
        Optional.ofNullable(locationUpdateRequest.getNewName()).ifPresent(name -> this.name = name);
    }

    public void increaseTotalCount(){
        this.totalCount++;
    }

    public void decreaseTotalCount() {
        this.totalCount--;
    }
}
