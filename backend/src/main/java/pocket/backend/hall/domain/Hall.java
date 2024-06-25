package pocket.backend.hall.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pocket.backend.hall.dto.HallUpdateRequestDTO;

import javax.swing.text.html.Option;
import java.util.Optional;

@NoArgsConstructor(access=lombok.AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String hallForm;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String menu;

    private int seat;

    @Column(nullable = false)
    private String weddingForm;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int count;

    @Builder
    public Hall(Long id, String name, String address, String phoneNumber, String hallForm, int price, String menu, int seat, String weddingForm, String image, String description, int count) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.hallForm = hallForm;
        this.price = price;
        this.menu = menu;
        this.seat = seat;
        this.weddingForm = weddingForm;
        this.image = image;
        this.description = description;
        this.count = count;
    }

    public void update(HallUpdateRequestDTO hallUpdateRequestDTO) {
        Optional.ofNullable(hallUpdateRequestDTO.getName()).ifPresent(name -> this.name = name);
        Optional.ofNullable(hallUpdateRequestDTO.getAddress()).ifPresent(address -> this.address = address);
        Optional.ofNullable(hallUpdateRequestDTO.getPhoneNumber()).ifPresent(phoneNumber -> this.phoneNumber = phoneNumber);
        Optional.ofNullable(hallUpdateRequestDTO.getHallForm()).ifPresent(hallForm -> this.hallForm = hallForm);
        Optional.ofNullable(hallUpdateRequestDTO.getPrice()).ifPresent(price -> this.price = price);
        Optional.ofNullable(hallUpdateRequestDTO.getMenu()).ifPresent(menu -> this.menu = menu);
        Optional.ofNullable(hallUpdateRequestDTO.getWeddingForm()).ifPresent(weddingForm -> this.weddingForm = weddingForm);
        Optional.ofNullable(hallUpdateRequestDTO.getImage()).ifPresent(image -> this.image = image);
        Optional.ofNullable(hallUpdateRequestDTO.getDescription()).ifPresent(description -> this.description = description);
        Optional.ofNullable(hallUpdateRequestDTO.getCount()).ifPresent(count -> this.count = count);
    }
}
