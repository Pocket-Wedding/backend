package pocket.backend.hall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pocket.backend.hall.domain.Hall;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HallResponseDTO {

    private Long id;
    private String name;
    private String address;
    private String hallForm;
    private Integer price;
    private String menu;
    private Integer seat;
    private String weddingForm;
    private String image;
    private String description;
    private Integer count;

    public static List<HallResponseDTO> listOf(List<Hall> filteredhalls) {
        return filteredhalls.stream()
                .map(HallResponseDTO::of)
                .toList();
    }

    private static HallResponseDTO of(Hall hall) {

        return new HallResponseDTO(

                hall.getId(),
                hall.getName(),
                hall.getAddress(),
                hall.getHallForm(),
                hall.getPrice(),
                hall.getMenu(),
                hall.getSeat(),
                hall.getWeddingForm(),
                hall.getImage(),
                hall.getDescription(),
                hall.getCount()

        );

    }

}
