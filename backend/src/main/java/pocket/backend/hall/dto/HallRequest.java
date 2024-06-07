package pocket.backend.hall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HallRequest {
    private String name;
    private String address;
    private String hallForm;
    private String price;
    private String menu;
    private int seat;
    private String weddingForm;
    private String image;
    private String description;
    private int count;
}
