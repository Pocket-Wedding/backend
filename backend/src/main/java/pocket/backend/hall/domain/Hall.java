package pocket.backend.hall.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
