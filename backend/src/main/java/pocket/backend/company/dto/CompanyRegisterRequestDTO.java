package pocket.backend.company.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@Getter
public class CompanyRegisterRequestDTO {

    @NotBlank
    private String name;

    private String describe;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotNull
    @Min(0)
    private Integer price;

    private String imageUrl;

    @NotBlank
    private String categoryName;

    @NotBlank
    private String locationName;
}
