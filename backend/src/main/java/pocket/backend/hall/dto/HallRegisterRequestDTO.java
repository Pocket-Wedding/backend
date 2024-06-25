package pocket.backend.hall.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class HallRegisterRequestDTO {

    @NotBlank
    String name;

    @NotBlank
    String address;

    @NotBlank
    String phoneNumber;

    @NotBlank
    String hallForm;

    @NotNull
    @Min(0)
    int price;

    @NotBlank
    String menu;

    int seat;

    @NotBlank
    String weddingForm;

    String image;

    @NotBlank
    String description;

    @NotNull
    int count;

}