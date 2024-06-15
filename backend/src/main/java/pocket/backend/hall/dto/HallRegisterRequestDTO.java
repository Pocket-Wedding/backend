package pocket.backend.hall.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@Getter
public class HallRegisterRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String hallForm;

    @NotBlank
    @Min(0)
    private Integer price;

    @NotBlank
    private String menu;

    private Integer seat;

    @NotBlank
    private String weddingForm;

    private String image;

    @NotBlank
    private String description;

    @NotBlank
    private Integer count;

}